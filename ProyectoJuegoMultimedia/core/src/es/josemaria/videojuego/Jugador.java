package es.josemaria.videojuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

public class Jugador {
    private Sprite sprite; //El sprite del jugador
    private Vector3 posicionTiles; //Posición x e y (z siempre 0) del jugador contando en tiles
    private Vector3 posicionPixels; //Posición x e y (z siempre 0) del jugador contando en pixels
    private OrthographicCamera camara; //Cámara que renderiza al jugado
    private int anchuraMapaPixels; //Anchura del mapa donde nos movemos en pixels
    private int alturaMapaPixels; //Altura del mapa donde nos movemos en pixels
    private int anchuraMapaTiles; //Anchura del mapa donde nos movemos en  tiles
    private int alturaMapaTiles; //Anchura del mapa donde nos movemos en tiles
    private SpriteBatch batch; //Batch donde contengo y dibujo al personaje
    private TiledMap mapa;
    /**
     * Constructor que permite crear un jugador a partir de su
     * caballero, el mapa donde se mueve, y la cámara que lo renderiza
     * Crea al jugador centrado  en la cámara
     * @param caballero false->pegaso true->dragon
     * @param cam objeto cámara que se usa en el juego
     * @param mapa mapa que se está renderizando actualmente
     */
    public Jugador(boolean caballero, OrthographicCamera cam, TiledMap mapa){
        batch=new SpriteBatch();
        camara=cam;
        anchuraMapaTiles = ((TiledMapTileLayer) mapa.getLayers().get(0)).getWidth(); //Obtenemos desde el mapa el número de tiles de ancho de la 1º Capa
        alturaMapaTiles = ((TiledMapTileLayer) mapa.getLayers().get(0)).getHeight(); //Obtenemos desde el mapa el número de tiles de alto de la 1º Capa
        anchuraMapaPixels=anchuraMapaTiles*(int)mapa.getProperties().get("width");
        alturaMapaPixels=alturaMapaTiles*(int)mapa.getProperties().get("height");
        this.posicionTiles=new Vector3(camara.position.x,camara.position.y,0);
        if(caballero){
            sprite=new Sprite(new Texture("personajes/dragon_juego.png"));
        }else{
            sprite=new Sprite(new Texture("personajes/pegaso_juego.png"));
        }
        this.mapa=mapa;
        this.ponerEnTile();

    }

    public Jugador(boolean genero, OrthographicCamera cam, TiledMap mapa,int posX,int posY){
        batch=new SpriteBatch();
        camara=cam;
        anchuraMapaTiles = ((TiledMapTileLayer) mapa.getLayers().get(0)).getWidth(); //Obtenemos desde el mapa el número de tiles de ancho de la 1º Capa
        alturaMapaTiles = ((TiledMapTileLayer) mapa.getLayers().get(0)).getHeight(); //Obtenemos desde el mapa el número de tiles de alto de la 1º Capa
        anchuraMapaPixels=anchuraMapaTiles*(int)mapa.getProperties().get("width");
        alturaMapaPixels=alturaMapaTiles*(int)mapa.getProperties().get("height");
        this.posicionTiles=new Vector3(posX,posY,0);
        if(genero){
            sprite=new Sprite(new Texture("personajes/dragon_juego.png"));
        }else{
            sprite=new Sprite(new Texture("personajes/pegaso_juego.png"));
        }

        this.ponerEnTile();

    }

    /**
     * Calcula a que pixel de la pantalla corresponde el tile que decimos
     */
    private void ponerEnTile(){
        posicionPixels=camara.project(new Vector3(posicionTiles));
        sprite.setPosition(posicionPixels.x,posicionPixels.y);
    }

    /**
     * Cambia la posición del personaje expresada en la coordenada del tile a donde va
     * @param x coordenada horizontal del tile donde se pone el jugador
     * @param y coordenada vertical del tile donde se pone el jugador
     */
    public void cambiarTile(int x,int y){
        posicionTiles=new Vector3(x,y,0);
        //ponerEnTile();
    }

    /**
     * Mueve el jugador un tile en la dirección establecida
     * @param direccion 'u' -> arriba,'d' -> abajo,'l' -> izda, 'r' -> derecha
     */
    public void moverJugadorTile(char direccion,boolean moverDelCentro){
        switch (direccion){
            case 'u':
                if(posicionTiles.y<this.alturaMapaTiles) {
                    posicionTiles.y++;
                }
                break;
            case 'd':
                if(posicionTiles.y>0) {
                    posicionTiles.y--;
                }
                break;
            case 'l':
                if(posicionTiles.x>0) {
                    posicionTiles.x--;
                }
                break;
            case 'r':
                if(posicionTiles.x<this.anchuraMapaTiles) {
                    posicionTiles.x++;
                }
                break;
        }
        this.cambiarTile((int)posicionTiles.x,(int)posicionTiles.y);
        if(moverDelCentro){
            ponerEnTile();
        }
    }

    public void moverJugadorPixels(char direccion){
        switch (direccion){
            case 'u':
                if(posicionPixels.y<this.alturaMapaPixels) {
                    posicionPixels.y+=Gdx.graphics.getDeltaTime()*300;
                }
                break;
            case 'd':
                if(posicionPixels.y>0) {
                    posicionPixels.y-=Gdx.graphics.getDeltaTime()*300;
                }
                break;
            case 'l':
                if(posicionPixels.x>0) {
                    posicionPixels.x-=Gdx.graphics.getDeltaTime()*300;
                }
                break;
            case 'r':
                if(posicionPixels.x<this.anchuraMapaPixels-1) {
                    posicionPixels.x+=Gdx.graphics.getDeltaTime()*300;
                }
                break;
        }
        sprite.setPosition(posicionPixels.x,posicionPixels.y);
    }


    public boolean moverCamaraTile(char direccion) {
        switch (direccion) {
            case 'u':
                if (camara.position.y < this.alturaMapaTiles) {
                    camara.position.y++;
                    return true;
                }
                break;
            case 'd':
                if (camara.position.y > 0) {
                    camara.position.y--;
                    return true;
                }
                break;
            case 'l':
                if (camara.position.x > 0) {
                    camara.position.x--;
                    return true;
                }
                break;
            case 'r':
                if (camara.position.x < this.anchuraMapaTiles) {
                    camara.position.x++;
                    return true;
                }
                break;
        }
        return false;
    }
    /**
     * Dibuja el jugador dentro de su batch interno
     */
    public void dibujar(){
        ajustarACamara();
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    //Borra las variables internas que ocupan memoria y no se liberan solas.
    public void dispose(){
        batch.dispose();
    }

    private void ajustarACamara(){
        sprite.setSize(
                ((Gdx.graphics.getWidth()*sprite.getTexture().getWidth())
                        /anchuraMapaPixels)*(1/camara.zoom),
                ((Gdx.graphics.getHeight()*sprite.getTexture().getHeight())
                        /alturaMapaPixels)
                        *(1/camara.zoom));
    }
    public OrthographicCamera getCamara(){
        return this.camara;
    }

    public TiledMap getMapa(){
        return this.mapa;
    }

}

