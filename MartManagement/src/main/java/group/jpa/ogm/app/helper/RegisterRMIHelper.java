package group.jpa.ogm.app.helper;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegisterRMIHelper {
    private static RegisterRMIHelper _instance;
    private static Registry registry;

    public Registry getRegistry() {
        return registry;
    }

    private RegisterRMIHelper(int port) throws RemoteException {
        registry = LocateRegistry.createRegistry(port);
    }

    public static RegisterRMIHelper getInstance(int port) throws RemoteException {
        if (_instance == null) {
            synchronized (RegisterRMIHelper.class) {
                if (null == _instance) {
                    _instance = new RegisterRMIHelper(port);
                }
            }
        }

        return _instance;
    }

    public void registerObject(String name, Remote remoteObj) throws AlreadyBoundException, RemoteException {
        registry.bind(name, remoteObj);
        System.out.println(String.format("Registered: %s -> %s", name, remoteObj.getClass().getSimpleName()));
    }
}

