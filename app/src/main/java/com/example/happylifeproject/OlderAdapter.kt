package com.example.happylifeproject

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OlderAdapter(private val activity: OlderActivity, var datas: MutableList<Item_card>) : RecyclerView.Adapter<OlderAdapter.ViewHolder>() {

    //这个类的构造函数接收一个 com.example.happy-life.OlderActivity 参数，然后我们使用这个参数来获取到数据库连接

    //使用 createStatement 方法创建一个 Statement 实例
//    private val statement = activity.getConnection()?.createStatement()
    //使用 executeQuery 方法来执行查询操作
//    private val resultSet: ResultSet? = statement?.let { it.executeQuery("SELECT * FROM activity") }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //使用 findViewById 方法来获取布局文件中的文本展示框
        val activityNameTextView: TextView = view.findViewById(R.id.activityNameTextView)
    }

    //加载了卡片的布局文件，并创建了 ViewHolder 实例
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return ViewHolder(view)
    }

    //返回数据库中记录的数量
    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //设置了卡片上的文本展示框的内容
//        resultSet?.absolute(position + 1)

        var item = datas.get(position)
        holder.activityNameTextView.text = item?.name
        //为卡片添加了点击事件处理器
        holder.itemView.setOnClickListener {
            //点击卡片时，会启动一个新的 OlderDetailActivity，并将卡片的编号（即 position 变量）传递给新的界面
            Log.d("position", position.toString())

            val intent = Intent(activity, OlderDetailActivity::class.java)
            intent.putExtra("position", position)
            activity.startActivity(intent)
        }
    }

}