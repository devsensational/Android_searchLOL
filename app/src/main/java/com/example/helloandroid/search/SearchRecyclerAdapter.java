package com.example.helloandroid.search;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.helloandroid.R;

import java.util.List;

/**
 * 검색화면의 RecyclerView를 구성하는 Adapter
 *
 * @author 고동현
 * @since 2021-11-20
 */
public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.SearchRecyclerViewHolder> {
    /**
     * 검색 화면 구성하는데 필요한 DataObject List
     */
    private List<SearchItemObject> itemObjectList;

    /**
     * 검색 화면 구성하는데 필요한 DataObject List를 Set한다.
     * notifyDataSetChanged()를 호출하여 RecyclerView를 다시 구성한다.
     *
     * @param itemObjectList itemObjectList
     */
    public void setItemObjectList(List<SearchItemObject> itemObjectList) {
        this.itemObjectList = itemObjectList;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder 구성 시 호출
     *
     * @param parent   parent View
     * @param viewType
     * @return ViewHolder
     */
    @NonNull
    @Override
    public SearchRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.search_item_view, parent, false);
        return new SearchRecyclerViewHolder(view);
    }

    /**
     * ViewHolder Binding시 호출
     *
     * @param holder   ViewHolder
     * @param position 위치
     */
    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerViewHolder holder, int position) {
        SearchItemObject searchItemObject = itemObjectList.get(position);
        holder.kdaTextView.setText(searchItemObject.getKda());
        String winText;
        String color;

        if (searchItemObject.isWin()) {
            winText = "승";
            color = "#b2ebf4";
        } else {
            winText = "패";
            color = "#ff9595";
        }
        holder.winTextView.setText(winText);
        holder.containerLayout.setBackgroundColor(Color.parseColor(color));
        //holder.gameTimeTextView.setText(searchItemObject.getGameTime());
        Glide.with(holder.championImageView.getContext()).load(searchItemObject.getChampion())
                .into(holder.championImageView);
        Glide.with(holder.itemImageView.getContext()).load(searchItemObject.getItem()[0])
                .into(holder.itemImageView);
        Glide.with(holder.item2ImageView.getContext()).load(searchItemObject.getItem()[1])
                .into(holder.item2ImageView);
        Glide.with(holder.item3ImageView.getContext()).load(searchItemObject.getItem()[2])
                .into(holder.item3ImageView);
        Glide.with(holder.item4ImageView.getContext()).load(searchItemObject.getItem()[3])
                .into(holder.item4ImageView);
        Glide.with(holder.item5ImageView.getContext()).load(searchItemObject.getItem()[4])
                .into(holder.item5ImageView);
        Glide.with(holder.item6ImageView.getContext()).load(searchItemObject.getItem()[5])
                .into(holder.item6ImageView);

        // 스펠 이미지 불러오는 코드
        Glide.with(holder.spellImageView.getContext()).load(searchItemObject.getSpell()[0])
                .into(holder.spellImageView);
        Glide.with(holder.spell2ImageView.getContext()).load(searchItemObject.getSpell()[1])
                .into(holder.spell2ImageView);
        // 룬 이미지 불러오는 코드
        Glide.with(holder.runesImageView.getContext()).load(searchItemObject.getRunes()[0])
                .into(holder.runesImageView);
        Glide.with(holder.runes2ImageView.getContext()).load(searchItemObject.getRunes()[1])
                .into(holder.runes2ImageView);

        // 장신구
        Glide.with(holder.totemImageView.getContext()).load(searchItemObject.getTotem())
                .into(holder.totemImageView);
    }

    /**
     * 전체 DataObject List 크기 return
     *
     * @return 전체 DataObject List 크기
     */
    @Override
    public int getItemCount() {
        return itemObjectList.size();
    }

    /**
     * 검색 RecyclerViewHolder를 구성
     */
    static class SearchRecyclerViewHolder extends RecyclerView.ViewHolder {
        /**
         * 전체 아이템 상위 컨테이너
         */
        private LinearLayout containerLayout;
        /**
         * 승률 Text
         */
        private TextView winTextView;
        /**
         * 게임 시간
         */
        private TextView gameTimeTextView;
        /**
         * kda
         */
        private TextView kdaTextView;
        /**
         * 챔피언 이미지
         */
        private ImageView championImageView;
        /**
         * 스펠 이미지
         */
        private ImageView spellImageView;
        /**
         * 스펠 이미지
         */
        private ImageView spell2ImageView;
        /**
         * 룬 이미지
         */
        private ImageView runesImageView;
        /**
         * 룬 이미지
         */
        private ImageView runes2ImageView;
        /**
         * 아이텤 이미지
         */
        private ImageView itemImageView;
        /**
         * 아이텤 이미지
         */
        private ImageView item2ImageView;
        /**
         * 아이텤 이미지
         */
        private ImageView item3ImageView;
        /**
         * 아이텤 이미지
         */
        private ImageView item4ImageView;
        /**
         * 아이텤 이미지
         */
        private ImageView item5ImageView;
        /**
         * 아이텤 이미지
         */
        private ImageView item6ImageView;
        /**
         * 장신구 이미지
         */
        private ImageView totemImageView;

        /**
         * 생성자로서 View를 바인딩한다.
         *
         * @param itemView inflatedView
         */
        public SearchRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            containerLayout = itemView.findViewById(R.id.search_item_linear_layout);
            championImageView = itemView.findViewById(R.id.championImageView);
            winTextView = itemView.findViewById(R.id.win_textView);
            kdaTextView = itemView.findViewById(R.id.kda_textView);
            spellImageView = itemView.findViewById(R.id.spell_imageView);
            spell2ImageView = itemView.findViewById(R.id.spell2_imageView);
            runesImageView = itemView.findViewById(R.id.runes_imageView);
            runes2ImageView = itemView.findViewById(R.id.runes2_imageView);
            itemImageView = itemView.findViewById(R.id.item_imageView);
            item2ImageView = itemView.findViewById(R.id.item2_imageView);
            item3ImageView = itemView.findViewById(R.id.item3_imageView);
            item4ImageView = itemView.findViewById(R.id.item4_imageView);
            item5ImageView = itemView.findViewById(R.id.item5_imageView);
            item6ImageView = itemView.findViewById(R.id.item6_imageView);
            totemImageView = itemView.findViewById(R.id.totem_imageView);
        }
    }
}
