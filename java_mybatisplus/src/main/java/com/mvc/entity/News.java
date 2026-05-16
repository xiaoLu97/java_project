package com.mvc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 新闻表
 * </p>
 *
 * @author ${author}
 * @since 2026-05-16
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class News implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 新闻ID
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 新闻标题
     */
      private String title;

      /**
     * 新闻内容
     */
      private String content;

      /**
     * 创建时间
     */
      private LocalDateTime createtime;

      /**
     * 操作人姓名
     */
      private String opername;


}
