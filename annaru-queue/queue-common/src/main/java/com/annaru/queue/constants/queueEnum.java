package com.annaru.queue.constants;

/**
 * Description: queue枚举
 * Author:jyehui
 * Date:2020/02/03
 */
public class queueEnum {

    /**
     * 启用状态
     */
    public enum enabledStatus {
        /**
         * 启用
         */
        ENABLED(0),
        /**
         * 禁用
         */
        DISABLE(1);

        public int value;

        enabledStatus(int value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 是否删除
     */
    public enum deleteStatus {
        /**
         * 未删除
         */
        NOTDELETE(0),
        /**
         * 已删除
         */
        ISDELETE(1);

        private int value;

        deleteStatus(int value){
            this.value = value;
        }

        public Integer getValue(){
            return this.value;
        }

    }


}
