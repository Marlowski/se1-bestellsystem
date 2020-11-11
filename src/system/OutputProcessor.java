package system;

import datamodel.Customer;
import datamodel.Order;
import datamodel.OrderItem;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

final class OutputProcessor implements Components.OutputProcessor {
    private InventoryManager imInstance;
    private OrderProcessor opInstance;

    OutputProcessor(InventoryManager imInstance, OrderProcessor opInstance) {
        this.imInstance = imInstance;
        this.opInstance = opInstance;
    }

    @Override
    public void printOrders(List<Order> orders, boolean printVAT) {
        StringBuffer sbAllOrders = new StringBuffer( "-------------" );
        StringBuffer sbLineItem = new StringBuffer();
        long totalPrice = 0;
        int printLineWidth = 95;

        for(Order currentOrder : orders) {
            // order number and customer name
            Customer customer = currentOrder.getCustomer();
            String customerName = splitName(customer, customer.getFirstName() + " " + customer.getLastName());
            sbLineItem.append("#").append(currentOrder.getId()).append(" ").append(customerName).append("'s Bestellung: ");
            Iterator<OrderItem> it = currentOrder.getItems().iterator();
            long currentPrice = 0;
            while(it.hasNext()) {
                // ordered item amount and additional description
                OrderItem currentArticle = it.next();
                sbLineItem.append(currentArticle.getUnitsOrdered()).append("x ");
                sbLineItem.append(currentArticle.getDescription());
                // calculate price for all articles bought by the current customer
                currentPrice = currentPrice + currentArticle.getUnitsOrdered() * currentArticle.getArticle().getUnitPrice();
                //check if a comma needs to be applied or not
                if(it.hasNext()) {
                    sbLineItem.append(", ");
                }
            }
            //add price of current order to the total price pool
            totalPrice = totalPrice + currentPrice;
            //append complet order to the list
            sbAllOrders.append( "\n" );
            sbAllOrders.append(fmtLine(sbLineItem.toString(), fmtPrice(currentPrice,"EUR",14), printLineWidth));

            //clear the single line buffers content
            sbLineItem.delete(0, sbLineItem.length());
        }

        // calculate total price
        String fmtPriceTotal = fmtPrice( totalPrice, "EUR");

        // append final line with totals
        sbAllOrders.append( "\n" )
                .append( fmtLine( "-------------", "-------------", printLineWidth) )
                .append( "\n" )
                .append( fmtLine( "Gesamtwert aller Bestellungen:", fmtPriceTotal, printLineWidth) );

        // print sbAllOrders StringBuffer with all output to System.out
        System.out.println( sbAllOrders.toString() );
    }

    @Override
    public void printInventory() { }

    @Override
    public String fmtPrice(long price, String currency) {
        StringBuffer fmtPriceSB = new StringBuffer();
        //convert
        double dblPrice = ( (double)price ) / 100.0;			// convert cent to Euro
        DecimalFormat df = new DecimalFormat( "#,##0.00",
                new DecimalFormatSymbols( new Locale( "de" ) ) );	// rounds double to 2 digits
        String fmtPrice = df.format( dblPrice );				// convert result to String in DecimalFormat
        fmtPriceSB.append( fmtPrice );
        //add postfix
        fmtPriceSB.append(" ").append(currency);
        //pad
        String fmtter = ("%") + 14 + "s";
        return String.format( fmtter, fmtPriceSB );
    }

    @Override
    public String fmtPrice(long price, String currency, int width) {
        StringBuffer fmtPriceSB = new StringBuffer();
        //convert
        double dblPrice = ( (double)price ) / 100.0;			// convert cent to Euro
        DecimalFormat df = new DecimalFormat( "#,##0.00",
                new DecimalFormatSymbols( new Locale( "de" ) ) );	// rounds double to 2 digits
        String fmtPrice = df.format( dblPrice );				// convert result to String in DecimalFormat
        fmtPriceSB.append( fmtPrice );
        //add postfix
        fmtPriceSB.append(" ").append(currency);
        //pad
        String fmtter = ("%") + width + "s";
        return String.format( fmtter, fmtPriceSB );
    }

    @Override
    public StringBuffer fmtLine(String leftStr, String rightStr, int width) {
        StringBuffer sb = new StringBuffer( leftStr );
        int shiftable = 0;		// leading spaces before first digit
        for( int i=1; rightStr.charAt( i ) == ' ' && i < rightStr.length(); i++ ) {
            shiftable++;
        }
        final int tab1 = width - rightStr.length() + 1;	// - ( seperator? 3 : 0 );
        int sbLen = sb.length();
        int excess = sbLen - tab1 + 1;
        int shift2 = excess - Math.max( 0, excess - shiftable );
        if( shift2 > 0 ) {
            rightStr = rightStr.substring( shift2, rightStr.length() );
            excess -= shift2;
        }
        if( excess > 0 ) {
            switch( excess ) {
                case 1:	sb.delete( sbLen - excess, sbLen ); break;
                case 2: sb.delete( sbLen - excess - 2 , sbLen ); sb.append( ".." ); break;
                default: sb.delete( sbLen - excess - 3, sbLen ); sb.append( "..." ); break;
            }
        }
        String strLineItem = String.format( "%-" + ( tab1 - 1 ) + "s%s", sb.toString(), rightStr );
        sb.setLength( 0 );
        sb.append( strLineItem );
        return sb;
    }

    /**
     *  Split single String name into first- and last name and set to the customer
     * @param customer object for which first- and lastName are set
     * @param name single-String name that is split into first- and last name
     * @return returns single String name extracted from customer object
     */
    @Override
    public String splitName(Customer customer, String name) {
        boolean swapName = false;
        if(name.contains(",")) {
            name = name.replace(",","");
            swapName = true;
        }
        String[] nameArray = name.split(" ");
        customer.setFirstName(nameArray[0]);
        for(int i=1; i < nameArray.length-1; i++) {
            customer.setFirstName(customer.getFirstName() + " " + nameArray[i]);
        }
        customer.setLastName(nameArray[nameArray.length-1]);
        if(swapName) {
            String tempVar = customer.getFirstName();
            customer.setFirstName(customer.getLastName());
            customer.setLastName(tempVar);
        }
        return customer.getLastName() + ", " + customer.getFirstName();
    }

    /**
     * returns cutomers full name
     * @param customer object referred to
     * @return full name as one String with "lastName, firstName"
     */
    @Override
    public String singleName(Customer customer) {
        return customer.getLastName() + ", " + customer.getFirstName();
    }
}

