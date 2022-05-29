package com.nullchecks;

public class Article {

    private Warranty moneyBackGuarantee;
    private Warranty expressWarranty;

    /**
     * This is the start point of the Article (at the beginning of its existence)
     */
    private Warranty effectiveExpressWarranty;

    public Article(Warranty moneyBackGuarantee, Warranty expressWarranty) {
        this(moneyBackGuarantee, expressWarranty, Warranty.VOID);
    }

    public Article(Warranty moneyBackGuarantee, Warranty expressWarranty, Warranty effectiveExpressWarranty) {
        // There will never be an object a null warranty
        if(moneyBackGuarantee == null) throw new IllegalArgumentException();
        if(expressWarranty == null) throw new IllegalArgumentException();

        this.moneyBackGuarantee = moneyBackGuarantee;
        this.expressWarranty = expressWarranty;
        this.effectiveExpressWarranty = effectiveExpressWarranty;
    }



    public Warranty getMoneyBackGuarantee() {
        return moneyBackGuarantee;
    }

    /**
     * Encapsulation is responsible to manage behavior, not data.
     * I may change my mind in the future, but I don't promise the internal state will remain the same, that is why it is maintained private
     * The value of effectiveExpressWarranty will depend on which situation we are
     * I setup the rules internally, and that is the meaning of encapsulation, manage/expose behavior with no side-effect on consumers
     * @return
     */
    public Warranty getExpressWarranty() {
//        return expressWarranty;
        return effectiveExpressWarranty;
    }

    /**
     * Report visible damage in case there are damages on the article
     * Use immutability to implement this validation
     * This method returns a fresh immutable copy of Article, which carries remember of damages inflicted to the object
     * No matter what guaranties we had before, it will return VOID guaranty in case of damage
     * @return
     */
    public Article withVisibilityDamage() {
        return new Article(Warranty.VOID, this.expressWarranty, this.effectiveExpressWarranty);
    }

    /**
     * When the machine stops working, the guarantee should return non-VOID
     * On this situation, I am providing the original guarantee
     * @return
     */
    public Article notOperational() {
        return new Article(this.moneyBackGuarantee, this.expressWarranty, this.expressWarranty);
    }
}
