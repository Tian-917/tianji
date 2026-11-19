package com.tianji.learning.controller;


import com.tianji.common.utils.BeanUtils;
import com.tianji.common.utils.CollUtils;
import com.tianji.learning.domain.po.PointsBoardSeason;
import com.tianji.learning.domain.query.PointsBoardQuery;
import com.tianji.learning.domain.vo.PointsBoardSeasonVO;
import com.tianji.learning.domain.vo.PointsBoardVO;
import com.tianji.learning.service.IPointsBoardSeasonService;
import com.tianji.learning.service.IPointsBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 学霸天梯榜 前端控制器
 * </p>
 *
 * @author Tian
 * @since 2026-11-19
 */
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class PointsBoardController {

    private final IPointsBoardService pointsBoardService;

    private final IPointsBoardSeasonService seasonService;

    /**
     * 分页查询指定赛季的积分排行榜
     * @param query
     * @return
     */
    @GetMapping
    public PointsBoardVO queryPointsBoardBySeason(PointsBoardQuery query){
        return pointsBoardService.queryPointsBoardBySeason(query);
    }

    /**
     * 查询赛季信息列表，只返回已经开始过的赛季
     * @return
     */
    @GetMapping("/seasons/list")
    public List<PointsBoardSeasonVO> queryPointsBoardSeasons(){
        // 1.获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 2.查询开始时间不晚于当前时间的赛季
        List<PointsBoardSeason> list = seasonService.lambdaQuery()
                .le(PointsBoardSeason::getBeginTime, now)
                .list();
        if (CollUtils.isEmpty(list)) {
            return CollUtils.emptyList();
        }
        // 3.转换为VO返回
        return BeanUtils.copyToList(list, PointsBoardSeasonVO.class);
    }
}
