package org.falcon.store.dtos;

/**
 * Order status enum.
 */
public enum OrderStatusEnum {
    /**
     * Order is saved in the DB and sent to Kafka.
     */
    PROCESSED,
    /**
     * Order was not completed and it is waiting for product availability in store.
     */
    WAITING,
    /**
     * There was product availability in store and the order is completed.
     */
    COMPLETED
}
