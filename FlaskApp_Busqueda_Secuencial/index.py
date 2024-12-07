from app import app
import route  # Importa las rutas para registrarlas con la app

if __name__ == '__main__':
    app.run(debug=True, port=5001)
