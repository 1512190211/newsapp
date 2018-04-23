package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CommentAdapetr extends BaseAdapter  {
    Context context;
    List<Comment> data;

    public CommentAdapetr(Context c, List<Comment> data){
        this.context = c;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        // 重用convertView
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item, null);
            holder.comment_name = (TextView) convertView.findViewById(R.id.comment_name);
            holder.comment_content = (TextView) convertView.findViewById(R.id.comment_content);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.comment_name.setText(data.get(i).getName());
        holder.comment_content.setText(data.get(i).getContent());
        return convertView;
    }

    /**
     * 添加一条评论,刷新列表
     * @param comment
     */
    public void addComment(Comment comment){
        data.add(comment);
        notifyDataSetChanged();
    }

   /* public void onClick(View v){
        switch (v.getId()){
            case R.id.comment_respond:
                if(edit_text2.getText().toString().equals("")){
                    Toast.makeText(context, "评论不能为空！", Toast.LENGTH_SHORT).show();
                }else{
                    // 生成评论数据
                    Comment comment = new Comment();
                    comment.setName("评论者"+(data.size()+1)+"：");
                    comment.setContent(edit_text2.getText().toString());
                    commentRespondAdapter.addComment(comment);
                    edit_text2.setText("");
                    Toast.makeText(context, "评论成功！", Toast.LENGTH_SHORT).show();
                }
            case R.id.comment_showrespond:
                if(isshow==true) {
                    //隐藏网页
                    rl_commentitem.setVisibility(View.GONE);
                    commentlist2.setVisibility(View.VISIBLE);
                    isshow=false;
                    break;
                }
                if(isshow==false){
                    //显示网页
                    rl_commentitem.setVisibility(View.VISIBLE);
                    commentlist2.setVisibility(View.GONE);
                    isshow=true;
                    break;
                }
        }
    }*/

    /**
     * 静态类，便于GC回收
     */
    public static class ViewHolder{
        TextView comment_name;
        TextView comment_content;
        TextView comment_respond;
        TextView comment_showrespond;
    }
}
