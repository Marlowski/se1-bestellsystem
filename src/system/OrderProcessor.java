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
        return Math.round(grossValue - (grossValue / 1.19));
    }

    @Override
    public long vat(long grossValue, int rateIndex) {
        try {
            if (rateIndex == 1) {
                return Math.round(grossValue - (grossValue / 1.19));
            } else if (rateIndex == 2) {
                return Math.round(grossValue - (grossValue / 1.07));
            }
            else { throw new IndexOutOfBoundsException("rateIndex can only be 1 (19%) or 2 (7%)!"); }
        } catch(IndexOutOfBoundsException e) {
            e.getMessage();
        }
        return 0;
    }
}
