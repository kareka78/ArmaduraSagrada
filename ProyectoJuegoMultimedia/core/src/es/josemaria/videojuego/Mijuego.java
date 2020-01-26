package es.josemaria.videojuego;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Mijuego extends ApplicationAdapter {
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private static int WIDTH;
    private static int HEIGHT;
    public static final float unitScale = 1/32f;
    private Jugador jugador;
    private boolean caballeroJugador;

    /**
     * Constructor de Mijuego, recibe el caballero del personaje
     * @param caballero false-> Pegaso , true -> Dragon
     */
    public Mijuego(boolean caballero){

        caballeroJugador=caballero;
    }

    /**
     * Sirve para inicializar variables, como el mundo, personajes, teclado, sprites...
     */
    @Override
    public void create () {

        //CREACIóN DEL MUNDO
        //Obtenemos la anchura y la altura de nuestra pantalla en pixels
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        //Cargamos el tilemap desde assets
        TiledMap map = new TmxMapLoader().load("mapas/PantallaInicio.tmx");
        //Establecemos el renderizado del mapa dividido en Tiles de 32 dp.
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);
        //Declaramos la cámara a través de la que veremos el mundo
        camera = new OrthographicCamera();
        //Establecemos el zoom de la cámara. 0.1 es más cercano que 1.
        camera.zoom=1f;
        //Obtenemos desde el mapa el número de tiles de ancho de la 1º Capa
        WIDTH = ((TiledMapTileLayer) map.getLayers().get(0)).getWidth();
        //Obtenemos desde el mapa el número de tiles de alto de la 1º Capa
        HEIGHT = ((TiledMapTileLayer) map.getLayers().get(0)).getHeight();
        //Sacamos por el log el número de tiles de ancho
        Gdx.app.log("Width",Float.toString(WIDTH));
        //Sacamos por el log el número de tiles de alto
        Gdx.app.log("Height",Float.toString(HEIGHT));
        //Establecemos la cámara, y le decimos cuanto tiene que ocupar. Por defecto enfoca al 0,0.
        camera.setToOrtho(false, WIDTH,HEIGHT);
        camera.position.x =10;  //Establecemos la posición x de la cámara en función del número de tiles de la anchura. Jugaremos con esto en clase
        camera.position.y = 3; //Establecemos la posición x de la cámara en función del número de tiles de la anchura. Jugaremos con esto en clase
        Gdx.app.log("camera x",Float.toString(camera.position.x));
        Gdx.app.log("camera x",Float.toString(camera.position.y));
        camera.update(); //Colocamos la Cámara.

        //Inicializar Sprites
        jugador=new Jugador(caballeroJugador,camera,map);

        //ESCUCHADORES
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new EscuchadorTecladoCamara(camera,map));
        multiplexer.addProcessor(new EscuchadorTecladoJugador(jugador));
        Gdx.input.setInputProcessor(multiplexer);

    }

    /**Función que se ejecuta 60 veces por segundo por defecto, y dibuja
     * en pantalla, calcula acciones, consecuencias, etc. Es el núcleo de nuestro juego
     */
    @Override
    public void render () {

        //Color de limpieza, evita que cuando se desplaza la cámara
        //se queden dibujos antiguos que ya no deberían estar alli.
        Gdx.gl.glClearColor(1, 0, 0, 1);
        //limpia el fondo de pantalla con el color indicado
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //indica los elementos que se meten en el batch

        camera.update();
        renderer.render(); //Renderizamos la vista


        renderer.setView(camera); //Establecemos la vista del mundo a través de la cámara.

        jugador.dibujar();

    }

    /**
     * Se llama al cerrar la pantalla de juego, se debe llamar aqui
     * al dispose de todos los elementos que tengan uno. La mayoría
     * de los de LibGdx la tiene.
     */
    @Override
    public void dispose () {
        jugador.dispose();
        renderer.dispose(); //Destruimos el objeto que renderiza un mapa, para no tener filtraciones de memoria
    }
}

