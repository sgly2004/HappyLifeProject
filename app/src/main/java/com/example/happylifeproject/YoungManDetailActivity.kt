package com.example.happylifeproject

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException


class YoungManDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_young_man_detail)

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


        val cancel : Button = findViewById(R.id.cancel)
        val participate : Button = findViewById(R.id.participate)

        participate.setOnClickListener {
            try {
                    //加载 MySQL JDBC 驱动程序
                    Class.forName("com.mysql.jdbc.Driver")

                    //连接到数据库并获取连接对象
                    val connection = DriverManager.getConnection(jdbcUrl, username, password)

                    // 使用 JDBC 驱动从数据库中读取数据
                    val statement = connection.createStatement()
                    val resultSet =
                        statement.executeQuery("SELECT * FROM activity WHERE id = $position")

                    val selectSQL =
                       "SELECT name,time,location,work_content,child_advice FROM activity where id = $position"

                    while (resultSet.next()) {
                        val name: String = resultSet.getString("name")
                        val time: String = resultSet.getString("time")
                        val location: String = resultSet.getString("location")
                        val work_content: String = resultSet.getString("work_content")
                        val child_advice: String = resultSet.getString("child_advice")

                        val insertSQL =
                            "INSERT INTO participate (name,time,location,work_content,child_advice) values(?,?,?,?,?)"
                        val preparedStatement: PreparedStatement = connection.prepareStatement(insertSQL)
                        preparedStatement.setString(1, name)
                        preparedStatement.setString(2, time)
                        preparedStatement.setString(3, location)
                        preparedStatement.setString(4, work_content)
                        preparedStatement.setString(5, child_advice)

                        preparedStatement.executeUpdate()
                     }
                }catch (e: SQLException) {
                    e.printStackTrace()
                }
        }
    }
}
