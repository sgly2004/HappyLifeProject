package com.example.happylifeproject

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class YoungManAdapter(private val activity: YoungManActivity, private val ItemLIst:List<Item_card>) :
    RecyclerView.Adapter<YoungManAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //使用 findViewById 方法来获取布局文件中的文本展示框
        val activityNameTextView: TextView = view.findViewById(R.id.activityNameTextView)
    }

    //加载了卡片的布局文件，并创建了 ViewHolder 实例
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return ViewHolder(view)
    }

    //返回数据库中记录的数量
    override fun getItemCount()= ItemLIst.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //设置了卡片上的文本展示框的内容
//        resultSet?.absolute(position + 1)

        val item = ItemLIst[position]
        holder.activityNameTextView.text = item.name

        //为卡片添加了点击事件处理器
        holder.itemView.setOnClickListener {
            //点击卡片时，会启动一个新的 OlderDetailActivity，并将卡片的编号（即 position 变量）传递给新的界面
            val intent = Intent(activity, YoungManDetailActivity::class.java)
            intent.putExtra("position", position)
            activity.startActivity(intent)
        }
    }
}