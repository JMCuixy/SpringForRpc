package org.springframework.rpc.rmi;

import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

/**
 * Created by XiuYin.Cui on 2018/5/14.
 */
public class RmiExporter extends RmiServiceExporter {

    @Override
    protected Registry getRegistry(String registryHost, int registryPort, RMIClientSocketFactory clientSocketFactory,
                                   RMIServerSocketFactory serverSocketFactory) throws RemoteException {


        if (registryHost != null) {
            try {
                if (logger.isInfoEnabled()) {
                    logger.info("Looking for RMI registry at port '" + registryPort + "' of host [" + registryHost + "]");
                }
                //把spring源代码中这里try起来，报异常就创建一个
                Registry reg = LocateRegistry.getRegistry(registryHost, registryPort, clientSocketFactory);
                testRegistry(reg);
                return reg;
            } catch (RemoteException ex) {
                LocateRegistry.createRegistry(registryPort);
                Registry reg = LocateRegistry.getRegistry(registryHost, registryPort, clientSocketFactory);
                testRegistry(reg);
                return reg;
            }
        } else {
            return getRegistry(registryPort, clientSocketFactory, serverSocketFactory);
        }
    }
}
