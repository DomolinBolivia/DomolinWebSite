class DetailDevicePage extends Page {

    async onStart() {

        let httpReqIcon = new HttpGet(`services/device/getIconDevice?code=${this.code}`);
        let httpResIcon = await httpReqIcon.execute();
        let iconResult = httpResIcon.getJson();

        let texticon = this.findViewById("deviceimg");
        await texticon.setImageResource(`data:image/${iconResult.iconFormat};base64, ${iconResult.iconBase64}`);

        let descIcon = this.findViewById("descIcon");
        await descIcon.setText(this.name);

        let httpReq = new HttpGet(`services/deviceFile/getDeviceFile?id=${this.id}`);
        let httpRes = await httpReq.execute();
        let listDevicesFile = httpRes.getJson();

        let imgIcon = this.findViewById('imgIcon');
        await imgIcon.setBackground('pages/detail_device/img/bg_emply.9.png');
//        await imgIcon.setImageResource(`data:image/${listDevicesFile[0].iconFormat};base64,${listDevicesFile[0].iconBase64}`);

        let flowList = this.findViewById('flowList');

        for (let deviceFile of listDevicesFile)
        {
            let btnDevice = new LinkButton(this);
            btnDevice.deviceFile = deviceFile;
            await btnDevice.setIconWidth('100px');
            await btnDevice.setIconHeight('100px');
            btnDevice.setOnClickListener('onClickDevice');
            await btnDevice.setDrawableTop(`data:image/${deviceFile.iconFormat};base64, ${deviceFile.iconBase64}`);
            await flowList.addView(btnDevice);
        }

        let httpReqFotoReal = new HttpGet(`services/device/getDeviceFotoReal?id=${this.id}`);
        let httpResFotoReal = await httpReqFotoReal.execute();
        let fotoRealResult = httpResFotoReal.getJson();

        let deviceFoto = this.findViewById('deviceFoto');
        let deviceFotoReal = this.findViewById('deviceFotoReal');
        await deviceFotoReal.setText(this.description);
        await deviceFotoReal.setDrawableLeft(`data:image/${fotoRealResult.iconFormat};base64, ${fotoRealResult.iconBase64}`);
        await deviceFoto.addView(deviceFotoReal);

        let descCodigo = this.findViewById("descCodigo");
        await descCodigo.setText(this.code);

        this.findViewById('viewYouIns').setUrl(this.linkInstalation);
        this.findViewById('viewYouProm').setUrl(this.linkPromotion);

    }

    async onCreate(intent) {
        this.setContentView("pages/detail_device/DetailDeviceLayout.xml");
        this.id = intent.getExtra("device_id");
//        this.code = intent.getExtra("device_code");
//        this.name = intent.getExtra("device_name");
//        this.description = intent.getExtra("device_description");
//        this.linkInstalation = intent.getExtra("device_linkInstalation");
//        this.linkPromotion = intent.getExtra("device_linkPromotion");
    }
    async onClickDevice(view) {
        console.log(view.deviceFile);
        let imgIcon = this.findViewById('imgIcon');
        await imgIcon.setBackground('pages/detail_device/img/bg_emply.9.png');
        await imgIcon.setImageResource(`data:image/${view.deviceFile.iconFormat};base64,${view.deviceFile.iconBase64}`);
    }

    async onClickFinish() {
        this.finish();
    }

    async onClickDownloadPdf() {
        window.open(`services/device/getDataShet?id=${this.id}`);
    }
}
;