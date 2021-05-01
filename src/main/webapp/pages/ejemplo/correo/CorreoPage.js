class CorreoPage extends Dialog  {
    constructor(intent){
        super(intent);
        this.setContentView("pages/ejemplo/correo/correo_layout.xml");
    }
    
    // Se llama a este metodo cuando termina de cargar la pagina
    async onStart(){
        
        
    }
    
   async onClickSendData(view){  
       // Toast.makeText(this,"Me hiciste click",Toast.LENGTH_SHORT);
//       let obtieneData = function(){
//       let textEmail = this.findViewById("textEmail").getText();
//       let textPhone = this.findViewById("textPhone").getText();
//       let textDetail = this.findViewById("textDetail").getText()
//       
//       console.log(textEmail);
//       console.log(textPhone);
//       console.log(textDetail);
//        
//       };
       let botton = this.findViewById('botton');
       //botton.setOnClickListener("click",obtieneData);       
       botton.setOnClickListener(async view =>{
       //async onClick(view ) {
            let textEmail = this.findViewById('textEmail').getText();
            let textPhone = this.findViewById('textPhone').getText();
            let textDetail = this.findViewById('textDetail').getText();
       
       console.log(textEmail); 
       console.log(textPhone);
       console.log(textDetail);
      
       });
   }
};


