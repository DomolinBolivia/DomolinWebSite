class EjemploPage extends Page{
    constructor(intent){
        super(intent);
        this.setContentView("pages/ejemplo/ejemplo_layout.xml");
    }
    
    // Se llama a este metodo cuando termina de cargar la pagina
    async onStart(){
        
    }
    
    async onClickMeHiciste(view){
        Toast.makeText(this,"Me hiciste click",Toast.LENGTH_SHORT);
    }
};