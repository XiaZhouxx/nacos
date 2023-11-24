package com.xz.nacos.algorithm;

/**
 * @author xz
 * @since 2023/11/23 11:29
 */
public class DAGThread extends Thread {
    private int parentId;
    private int taskId;
    private final int FAIL = -1;
    private final int SUCCESS = 1;
    volatile int status;

    @Override
    public void run() {
        // 当依赖/入度为0时，则可以执行
        try {
            // 也就是判断是否有父任务，如果没有/父任务都执行完成，那么这个任务才能执行
        } catch (Exception ex) {
            status = FAIL;
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        // 当前节点执行完毕，设置状态为执行完成，其他依赖该任务的感知
        status = SUCCESS;
    }
}
