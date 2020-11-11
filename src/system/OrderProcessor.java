package system;

import datamodel.Order;
import datamodel.OrderItem;
import java.util.function.Consumer;

final class OrderProcessor implements Components.OrderProcessor {
    private InventoryManager invManInstance;

    OrderProcessor(InventoryManager instance) {
        this.invManInstance = instance;
    }

    @Override
    public boolean accept(Order order) {
        return false;
    }

    @Override
    public boolean accept(Order order, Consumer<Order> acceptCode, Consumer<Order> rejectCode, Consumer<OrderItem> rejectedOrderItemCode) {
        return false;
    }

    @Override
    public long orderValue(Order order) {
        return 0;
    }

    @Override
    public long vat(long grossValue) {
        return 0;
    }

    @Override
    public long vat(long grossValue, int rateIndex) {
        return 0;
    }
}
