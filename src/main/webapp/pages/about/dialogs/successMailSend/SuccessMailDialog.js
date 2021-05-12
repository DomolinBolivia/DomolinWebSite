class SuccessMailDialog extends Dialog  {
    constructor(intent) {

        super(intent);
        this.setContentView("pages/about/dialogs/successMailSend/SuccessMailLayout.xml");
        
    }
    
    async onClickClose(){ 
        
        this.cancel();
    }
    };

