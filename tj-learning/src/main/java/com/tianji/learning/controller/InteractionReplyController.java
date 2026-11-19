package com.tianji.learning.controller;


import com.tianji.common.domain.dto.PageDTO;
import com.tianji.learning.domain.dto.ReplyDTO;
import com.tianji.learning.domain.query.ReplyPageQuery;
import com.tianji.learning.domain.vo.ReplyVO;
import com.tianji.learning.service.IInteractionReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 互动问题的回答或评论 前端控制器
 * </p>
 *
 * @author Tian
 * @since 2026-11-19
 */
@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class InteractionReplyController {

    private final IInteractionReplyService interactionReplyService;

    /**
     * 新增回答或评论
     * @param replyDTO
     */
    @PostMapping
    public void saveReply(@Valid @RequestBody ReplyDTO replyDTO) {
        interactionReplyService.saveReply(replyDTO);
    }

    /**
     * 分页查询回答或评论
     * @param replyPageQuery
     * @return
     */
    @GetMapping("/page")
    public PageDTO<ReplyVO> queryReplyPage(@Valid ReplyPageQuery replyPageQuery) {
        return interactionReplyService.queryReplyPage(replyPageQuery, false);
    }
}
