package com.smegenys.marveldesigndemo;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

;

public class ListAdapter extends RecyclerView.Adapter<ListItem> {
MyListData[] listData;
int height;
public static int color;

  public  ListAdapter(MyListData[] listdata)
  {
      this.listData = listdata;
  }

    @NonNull
            @Override
            public ListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                // get size of device screen
                 height = parent.getMeasuredHeight() / 2;

                return new ListItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list, parent, false), height);

            }

            @Override
            public void onBindViewHolder(@NonNull ListItem holder, int position) {

                final MyListData myListData = listData[position];
                holder.bind(position);
                holder.txtname.setText(listData[position].getDescription());
                holder.superheroImage.setImageResource(listData[position].getImgId());
                holder.cardlayout.setCardBackgroundColor(holder.itemView.getResources().getColor(myListData.getColor()));
                holder.nickname.setText(listData[position].getNickname());
                color=listData[position].getColor();


               // holder.cardlayout.setCardBackgroundColor(R.color.yellow);
                //holder.cardlayout.setCardBackgroundColor(holder.getClass().getResource);


              //  GradientDrawable drawable= (GradientDrawable) holder.cardlayout.getBackground().getCurrent();




            }

            @Override public int getItemCount() {   // return number of item

                    return listData.length;
            }

            public void removeTopItem() {
                int removeIndex = 0;

                MyListData firstData = listData[0];
                for(int i = removeIndex; i < listData.length -1; i++){
                    listData[i] = listData[i+1];
                }
                listData[listData.length -1] = firstData;
                  notifyDataSetChanged();

            }





}
