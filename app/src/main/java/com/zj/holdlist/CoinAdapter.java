package com.zj.holdlist;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zj.holdlist.databinding.ItemCoinValueBinding;
import com.zj.holdlist.databinding.ItemDataValueBinding;

import java.util.List;

/**
 * create by zj on 2018/11/28
 */
public class CoinAdapter extends AbsRVAdapter<CoinBean,AbsRVAdapter.BindingViewHolder> {
    private final static int TYPE_NAME=0;
    private final static int TYPE_DATA=1;
    public CoinAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        ViewDataBinding binding;
        if (i==TYPE_NAME){
            binding=ItemCoinValueBinding.inflate(mInflater,parent,false);
        }else {
            binding= ItemDataValueBinding.inflate(mInflater,parent,false);
        }
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, int position) {
        CoinBean bean= (CoinBean) getItem(position);
        if (holder.mBinding instanceof ItemCoinValueBinding){
            ItemCoinValueBinding binding= (ItemCoinValueBinding) holder.mBinding;
            binding.setData(bean);
            binding.setPos(position);
        }else if (holder.mBinding instanceof ItemDataValueBinding){
            ItemDataValueBinding binding= (ItemDataValueBinding) holder.mBinding;
            binding.setData(bean);
            binding.setPos(position);
        }

    }

    @Override
    public int getItemViewType(int position) {
        List<CoinBean> list=getDataList();
        if (list!=null){
            return list.get(position).viewType==0?TYPE_NAME:TYPE_DATA;
        }else{
            return super.getItemViewType(position);
        }
    }
}
