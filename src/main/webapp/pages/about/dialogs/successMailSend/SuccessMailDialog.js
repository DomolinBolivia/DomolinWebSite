class SuccessMailDialog extends Dialog {
    constructor(intent) {
        super(intent);
        this.setContentView("pages/about/dialogs/successMailSend/SuccessMailLayout.xml");
    }
    
    // @Override
    async onStart(){
        await Thread.sleep(5000);
        if(this.isVisible())
            this.cancel();
    }

    async onClickClose() {
        this.cancel();
    }
}
;