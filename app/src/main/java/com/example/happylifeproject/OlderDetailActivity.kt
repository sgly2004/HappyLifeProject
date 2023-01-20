package com.example.happylifeproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.sql.DriverManager

class OlderDetailActivity : AppCompatActivity() {
    //var datas = mutableListOf<Item_card>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_older_detail)

        // 获取传递过来的卡片编号(以知道点击的卡片应该获取数据库中的哪些数据)
        val position = intent.getIntExtra("position", 0)

        val jdbcUrl = "jdbc:mysql://10.38.7.96:3306/activities?useSSL=false"
        val username = "root"
        val password = "root"

        //创建一个新线程
        Thread {
            //加载 MySQL JDBC 驱动程序
            Class.forName("com.mysql.jdbc.Driver")

            //连接到数据库并获取连接对象
            val connection = DriverManager.getConnection(jdbcUrl, username, password)

            //使用 connection 属性来获取到数据库连接
            // 使用 JDBC 驱动从数据库中读取数据
            val statement = connection.createStatement()
            val resultSet =
                statement.executeQuery("SELECT * FROM activity WHERE id = $position")
            //更新UI需要在主线程
            runOnUiThread {
                val activityNameTextView: TextView = findViewById(R.id.activityNameTextView)
                val timeTextView: TextView = findViewById(R.id.timeTextView)
                val locationTextView: TextView = findViewById(R.id.locationTextView)
                val workContentTextView: TextView = findViewById(R.id.workContentTextView)
                val childAdviceTextView: TextView = findViewById(R.id.childAdviceTextView)

                // Log.d("ResultSet", "activity_name:"+resultSet.getString("name"))
                // Log.d("ResultSet", "time:"+resultSet.getString("time"))
                //  Log.d("ResultSet", "location:"+resultSet.getString("location"))
                // Log.d("ResultSet", "work_content:"+resultSet.getString("work_content"))
                //Log.d("ResultSet", "child_advice:"+resultSet.getString("child_advice"))

                // 将数据填充到文本展示框中
                if (resultSet.next()) {
                    activityNameTextView.text = resultSet.getString("name")
                    timeTextView.text = resultSet.getString("time")
                    locationTextView.text = resultSet.getString("location")
                    workContentTextView.text = resultSet.getString("work_content")
                    childAdviceTextView.text = resultSet.getString("child_advice")
                }

            }
        }.start()
        }
    }
