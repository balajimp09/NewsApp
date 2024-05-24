package com.sur.newsapp


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.sur.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private var newsData : NewsData? = null
    private var articleList = arrayListOf<Article>()
    private lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        progressDialog = ProgressDialog(this)
        newsApiCall()
    }

    private fun newsApiCall() {
        progressDialog.showProgressDialog()
        val url = "https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json"
        ApiClient { response ->
            progressDialog.dismissProgressDialog()
            if (response != null) {
                val gson = Gson()
                newsData = gson.fromJson(response, NewsData::class.java)
                articleList.addAll(newsData?.articles as ArrayList<Article>)
                setUpRecyclerView()
                binding?.rvNewsData?.visibility = View.VISIBLE
                binding?.tvNoData?.visibility = View.GONE
            } else {
                binding?.rvNewsData?.visibility = View.GONE
                binding?.tvNoData?.visibility = View.VISIBLE
            }
        }.execute(url)
    }

    private fun setUpRecyclerView() {
        binding?.rvNewsData?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = NewsDataAdapter(
                R.layout.item_news_layout,
                list = articleList,
                br = BR.data,
                clickListener = { view, position ->
                    when (view.id) {
                        R.id.newsCard ->{
                            val webpage: Uri = Uri.parse(articleList[position].url)
                            val intent = Intent(Intent.ACTION_VIEW).setData(webpage)
                            try{
                                startActivity(intent)
                            } catch (e:Exception){
                                Toast.makeText(context, "Web browser Not Found. Please Install A Web Browser.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            )
        }
    }
}