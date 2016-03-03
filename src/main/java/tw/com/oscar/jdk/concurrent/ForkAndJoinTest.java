/**
 * ForkAndJoinTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/2/29
 * <p>
 * H i s t o r y
 * 2016/2/29 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * <p>
 * Title: ForkAndJoinTest.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/2/29
 * @since 2016/2/29
 */
public class ForkAndJoinTest {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
        forkJoinPool.invoke(myRecursiveAction);

        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);
        long mergedResult = forkJoinPool.invoke(myRecursiveTask);
        System.out.println("mergedResult = " + mergedResult);
    }

    static class MyRecursiveAction extends RecursiveAction {

        private long workLoad = 0;

        public MyRecursiveAction(long workLoad) {
            this.workLoad = workLoad;
        }

        @Override
        protected void compute() {
            // if work is above threshold, break tasks up into smaller tasks
            if (this.workLoad > 16) {
                System.out.println("Splitting workLoad : " + this.workLoad);

                List<MyRecursiveAction> subTasks = new ArrayList<>();

                subTasks.addAll(createSubtasks());

                subTasks.forEach(java.util.concurrent.RecursiveAction::fork);

            } else {
                System.out.println("Doing workLoad myself: " + this.workLoad);
            }
        }

        private List<MyRecursiveAction> createSubtasks() {
            List<MyRecursiveAction> subTasks = new ArrayList<>();

            MyRecursiveAction subtask1 = new MyRecursiveAction(this.workLoad / 2);
            MyRecursiveAction subtask2 = new MyRecursiveAction(this.workLoad / 2);

            subTasks.add(subtask1);
            subTasks.add(subtask2);

            return subTasks;
        }
    }

    static class MyRecursiveTask extends RecursiveTask<Long> {

        private long workLoad = 0;

        public MyRecursiveTask(long workLoad) {
            this.workLoad = workLoad;
        }

        @Override
        protected Long compute() {
            //if work is above threshold, break tasks up into smaller tasks
            if (this.workLoad > 16) {
                System.out.println("Splitting workLoad : " + this.workLoad);

                List<MyRecursiveTask> subTasks = new ArrayList<>();
                subTasks.addAll(createSubtasks());

                subTasks.forEach(ForkAndJoinTest.MyRecursiveTask::fork);

                long result = 0;
                for (MyRecursiveTask subtask : subTasks) {
                    result += subtask.join();
                }
                return result;

            } else {
                System.out.println("Doing workLoad myself: " + this.workLoad);
                return workLoad * 3;
            }
        }

        private List<MyRecursiveTask> createSubtasks() {
            List<MyRecursiveTask> subTasks = new ArrayList<>();

            MyRecursiveTask subtask1 = new MyRecursiveTask(this.workLoad / 2);
            MyRecursiveTask subtask2 = new MyRecursiveTask(this.workLoad / 2);

            subTasks.add(subtask1);
            subTasks.add(subtask2);

            return subTasks;
        }
    }
}
