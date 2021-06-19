/* global Thread, Resource, Constants, Toast, Config */
(async () => {
    await Resource.import("pages/home/style.css");
})();

class DesarrolloDialog extends Dialog{
    constructor(context){
        super(context);
        this.setContentView("pages/home/DesarrolloLayout.xml");
    }
    
    async onClickCerrar(){
        this.cancel();
    }
};

class HomePage extends Page {
    // @Override
    async onCreate(intent) {
        this.setContentView("pages/home/HomeLayout.xml");
        this.fotoActual = 1;
        this.NRO_IMAGENES = 4;
        this.timerFotos = new Timer();
    }

    // @Override
    async onStart() {
        this.imgFoto = this.findViewById("imgFoto");
        this.runnable = new Runnable();
        // let this_ = this;
        this.runnable.run=()=>{
            this.siguienteFoto();
        };
        this.timerFotos.schedule(this.runnable,3000); 
       
//        let dialog = new DesarrolloDialog(this);
//        await dialog.show();
        
        await this.loadListDevices();
    }

    //@Override
    async onPause(){
        console.log("On pause");
        this.timerFotos.stop();
    }

    //@Override
    async onDestroy(){
        console.log("On destroy");
        this.timerFotos.stop();
    }

    //@Override
    async onResume(){
        console.log("On resume");
        this.timerFotos.start();
    }

    async onClickPruebaGratuita(){
        window.open(Config.LOGIN_DOMOLIN);
    }

    async siguienteFoto(){
        (this.findViewById(`txtFoto${this.fotoActual}`)).setBackground(null);
        this.fotoActual++;
        if(this.fotoActual > 4)
            this.fotoActual = 1;
        this.imgFoto.setImageResource(`pages/home/photos/ic_photo_${this.fotoActual}.png`);
        (this.findViewById(`txtFoto${this.fotoActual}`)).setBackground("pages/home/imgs/bg_nro_img.png");
    }

    async anteriorFoto(){
        (this.findViewById(`txtFoto${this.fotoActual}`)).setBackground(null);
        this.fotoActual--;
        if(this.fotoActual<1)
            this.fotoActual = 4;
        this.imgFoto.setImageResource(`pages/home/photos/ic_photo_${this.fotoActual}.png`);
        (this.findViewById(`txtFoto${this.fotoActual}`)).setBackground("pages/home/imgs/bg_nro_img.png");
    }

    async onClickSiguienteFoto(){
        this.siguienteFoto();
    }

    async onClickAnteriorFoto(){
        this.anteriorFoto();
    }
    
    
    async onClickHome(){
        Toast.makeText(this,"Ya se encuentra en la página",Toast.LENGTH_SHORT);
    }
    
    async onClickDescargas(){
        await this.finish();
        await Resource.import("pages/downloads/DownloadPage.js");
        let intent = new Intent(this,"DownloadPage");
        this.startPage(intent);
    }
    
    async onClickAcercaDe(){
        await this.finish();
        await Resource.import("pages/about/AboutPage.js");
        let intent = new Intent(this,"AboutPage");
        this.startPage(intent);
    }
    
    async onClickLogin(){
        sessionStorage.clear();
        window.location.replace(Config.LOGIN_DOMOLIN);
    }
    async loadListDevices(){
        
        let httpReq = new HttpGet(`services/device/listDevice`);
        let httpRes = await httpReq.execute();
        let listDevices = httpRes.getJson();
        let flowDevices = this.findViewById('flowDevices');
        for(let device of listDevices){
            
            let btnDevice = new LinkButton(this);
            btnDevice.device = device;
            await btnDevice.setText(device.name);
        
        
            let httpReqIcon = new HttpGet(`services/device/getIconDevice?code=${device.code}`);
            let httpResIcon = await httpReqIcon.execute();
            let iconResult = httpResIcon.getJson();
            await btnDevice.setMarginLeft('30px');
            await btnDevice.setMarginRight('30px');
            await btnDevice.setIconWidth('100px');
            await btnDevice.setIconHeight('100px');
            btnDevice.setOnClickListener('onClickDevice');
            await btnDevice.setDrawableTop(`data:image/${iconResult.iconFormat};base64, ${iconResult.iconBase64}`);
            await flowDevices.addView(btnDevice);
        }                         
    }
    async onClickDevice(view){
        
        //await Toast.makeText(this.getContext(),'DISP: '+view.device.code,Toast.LENGTH_SHORT);
        await Resource.import("pages/detail_device/DetailDevicePage.js");
        let intent = new Intent(this,"DetailDevicePage");
        intent.putExtra("device_id",view.device.id);
        intent.putExtra("device_code",view.device.code);
        intent.putExtra("device_name",view.device.name);
        intent.putExtra("device_description",view.device.description);              
        intent.putExtra("device_linkInstalation",view.device.linkinstalation);
        intent.putExtra("device_linkPromotion",view.device.linkpromotion);               
        this.startPage(intent);
                                
    }
};