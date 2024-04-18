import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

// Clase que representa un paquete y su estado
class Package {
    private String id;
    private String status;
    private String location;

    public Package(String id) {
        this.id = id;
        this.status = "En tránsito";
        this.location = "Centro de distribución";
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    // Método para simular el cambio de estado y ubicación del paquete
    public void updateStatusAndLocation(String newStatus, String newLocation) {
        this.status = newStatus;
        this.location = newLocation;
    }
}

// Clase que simula el sistema de seguimiento en tiempo real de entregas de paquetes
public class RealTimePackageTrackingSystem {
    public static void main(String[] args) {
        // Creamos un flujo de paquetes
        Observable<Package> packageStream = Observable.create(emitter -> {
            // Simulamos la emisión de eventos de cambio de estado y ubicación de los paquetes
            Package package1 = new Package("001");
            Package package2 = new Package("002");

            // Emitimos los paquetes iniciales
            emitter.onNext(package1);
            emitter.onNext(package2);

            // Simulamos cambios de estado y ubicación cada 3 segundos
            while (!emitter.isDisposed()) {
                Thread.sleep(3000); // Simulamos un intervalo de 3 segundos
                package1.updateStatusAndLocation("En tránsito", "Centro de distribución");
                package2.updateStatusAndLocation("Entregado", "Domicilio del cliente");

                // Emitimos los paquetes actualizados
                emitter.onNext(package1);
                emitter.onNext(package2);
            }
        });

        // Suscribimos a los eventos del flujo de paquetes para recibir actualizaciones en tiempo real
        packageStream
                .subscribeOn(Schedulers.io()) // Ejecutamos el flujo en un hilo de fondo
                .observeOn(Schedulers.single()) // Procesamos las actualizaciones en un solo hilo
                .subscribe(packageData -> {
                    System.out.println("Paquete ID: " + packageData.getId());
                    System.out.println("Estado: " + packageData.getStatus());
                    System.out.println("Ubicación: " + packageData.getLocation());
                    System.out.println("---------");
                }, Throwable::printStackTrace);
    }
}

