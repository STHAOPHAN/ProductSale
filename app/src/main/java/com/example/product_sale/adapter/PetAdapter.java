package com.example.product_sale.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.product_sale.R;
import com.example.product_sale.activity.PetDetailActivity;
import com.example.product_sale.models.Cart;
import com.example.product_sale.models.CartItem;
import com.example.product_sale.models.Pet;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {
    private Context context;
    private List<Pet> petList;
    private Cart cart;

    public PetAdapter(Context context, List<Pet> petList, Cart cart) {
        this.context = context;
        this.petList = petList;
        this.cart = cart;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Pet pet = petList.get(position);
        holder.tvPetBreed.setText("Giống loài: " + pet.getBreed());
        holder.tvPetName.setText("Tên: " + pet.getName());
        holder.tvPetColor.setText("Màu: " + pet.getColor());
        holder.tvPetPrice.setText("Giá: " + pet.getPrice() + " VND");

        // Tìm resource ID của ảnh từ tên ảnh
        int imageResId = context.getResources().getIdentifier(pet.getImage(), "drawable", context.getPackageName());

        // Tải và hiển thị ảnh sử dụng Glide
        Glide.with(holder.itemView.getContext())
                .load(imageResId)
                .placeholder(R.drawable.default_pet_image) // Ảnh mặc định khi đang tải
                .error(R.drawable.default_pet_image) // Ảnh hiển thị khi có lỗi
                .into(holder.ivPet);

        holder.btnAddToCart.setOnClickListener(v -> {
            CartItem cartItem = new CartItem(pet);
            Cart.AddItemStatus result = cart.addItem(cartItem);
            if (result == Cart.AddItemStatus.ITEM_ALREADY_EXISTS) {
                Toast.makeText(context, "Pet is already in the cart", Toast.LENGTH_SHORT).show();
            } else if (result == Cart.AddItemStatus.ITEM_ADDED_SUCCESSFULLY) {
                Toast.makeText(context, "Added to the cart", Toast.LENGTH_SHORT).show();
            }
        });

        // Set the click listener for item view
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PetDetailActivity.class);
            intent.putExtra("pet", pet);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPet;
        TextView tvPetName, tvPetBreed, tvPetColor, tvPetPrice;
        ImageButton btnAddToCart;

        public PetViewHolder(View itemView) {
            super(itemView);
            ivPet = itemView.findViewById(R.id.iv_pet);
            tvPetName = itemView.findViewById(R.id.tv_pet_name);
            tvPetBreed = itemView.findViewById(R.id.tv_pet_breed);
            tvPetColor = itemView.findViewById(R.id.tv_pet_color);
            tvPetPrice = itemView.findViewById(R.id.tv_pet_price);
            btnAddToCart = itemView.findViewById(R.id.btn_add_to_cart);
        }
    }
}