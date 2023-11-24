package com.xz.nacos.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xz
 * @since 2023/11/23 11:25
 */
public class DAGTaskScheduler {
    private Map<Thread, List<Thread>> dagTasks = new HashMap<>();

    public void addTask(Thread task, List<Thread> depends) {
        dagTasks.put(task, depends);
    }

}
