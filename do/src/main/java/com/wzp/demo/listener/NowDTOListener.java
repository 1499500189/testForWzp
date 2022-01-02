package com.wzp.demo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wzp.demo.Dto.ExcelStudentDTO;
import com.wzp.demo.Dto.NowTestDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author
 * @date 2021 年 11 月 08 日
 */
@Slf4j
public class NowDTOListener extends AnalysisEventListener<NowTestDTO> {

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(NowTestDTO data, AnalysisContext context) {
        log.info("解析到一条数据:{}", data);

    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！");
    }
}
