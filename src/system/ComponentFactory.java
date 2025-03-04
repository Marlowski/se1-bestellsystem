package system;

import datamodel.RawDataFactory;

public final class ComponentFactory {
    private static ComponentFactory instance = null;
    private InventoryManager inventoryManager;
    private OrderProcessor orderProcessor;
    private OutputProcessor outputProcessor;
    private DataFactory dataFactory;

    /**
     * interface for the private system package
     */
    private ComponentFactory() {
        this.inventoryManager = new InventoryManager();
        this.orderProcessor = new OrderProcessor(inventoryManager);
        this.outputProcessor = new OutputProcessor(inventoryManager, orderProcessor);
        RawDataFactory.RawDataFactoryIntf objectRawFactory = RawDataFactory.getInstance(this);
        this.dataFactory = new DataFactory(objectRawFactory,inventoryManager,outputProcessor);
    }

    public static ComponentFactory getInstance() {
        if(instance == null) {
            instance = new ComponentFactory();
        }
        return instance;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public OrderProcessor getOrderProcessor() {
        return orderProcessor;
    }

    public OutputProcessor getOutputProcessor() {
        return outputProcessor;
    }

    public DataFactory getDataFactory() {
        return dataFactory;
    }
}
