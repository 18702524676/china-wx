package com.wx.permission.aspect;


import com.wx.permission.service.SysLogService;
import com.wx.permission.vo.SysLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName PermissionsLogThread
 * @Author wx
 * @Description 日志操作记录线程
 * @Date 2018-09-24-17:11
 */
@Slf4j
@Component
public class PermissionsLogThread implements Runnable {

	/** 无界队列 */
	private static BlockingQueue<SysLogVO> logInfoQueue = new LinkedBlockingQueue<SysLogVO>(1024);

	@Autowired
	private SysLogService sysLogService;

	/**
	 * @methodName: offerQueue
	 * @author: wx
	 * @description: 入队
	 * @param sysLogVO
	 * @date: 2018/9/24
	 * @return: void
	 */
	public void offerQueue(SysLogVO sysLogVO) {
		try {
			/** 添加元素后，会返回一个boolean值，看是否添加成功。若队列已满，再添加不会抛异常，
             但是返回false,表示添加失败                                                   */
			logInfoQueue.offer(sysLogVO);
		} catch (Exception e) {
			log.error("日志写入失败", e);
		}
	}
    
	/**
     * @methodName: run
     * @author: wx
     * @description: 执行写入日志
     * @param
     * @date: 2018/9/24
     * @return: void
     */
    @Override
	public void run() {
        // 缓冲队列
        while (true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<SysLogVO> bufferedLogList = new ArrayList<SysLogVO>();
            try {
                /**  take出队若队列为空，发生阻塞，等待有元素 */
                bufferedLogList.add(logInfoQueue.take());
                /** drainTo():一次性从BlockingQueue获取所有可用的数据对象（还可以指定获取数据的个数）并将这些元素添加到给定collection中，
                 　通过该方法，可以提升获取数据效率；不需要多次分批加锁或释放锁。 */
                logInfoQueue.drainTo(bufferedLogList);
                if (!CollectionUtils.isEmpty(bufferedLogList)) {
                    // 写入日志
                    for (SysLogVO log : bufferedLogList) {
                        sysLogService.saveSyslog(log);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 防止缓冲队列填充数据出现异常时不断刷屏
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            } finally {
                if (!CollectionUtils.isEmpty(bufferedLogList)) {
                    try {
                        bufferedLogList.clear();
                    } catch (Exception e) {
                    }
                }
            }
        }
	}
}
