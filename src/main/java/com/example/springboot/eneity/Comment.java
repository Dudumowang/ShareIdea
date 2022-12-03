package com.example.springboot.eneity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("pubid")
    private Integer pubId; //发布评论的ID

    @TableField("reid")
    private Integer reId; //评论回复的Idea的ID

    @TableField("content")
    private String content;  //评论内容

    @TableField("pub_time")
    private String pubTime;

    @TableField(exist = false)
    private String username;

    @TableField(exist = false)
    private String avatarUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getReId() {
        return reId;
    }

    public void setReId(Integer reId) {
        this.reId = reId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }
}
