package com.edgestackers.pgi.common.client;

import com.amtote.filebet.IService;
import com.amtote.gws.Gws;
import com.amtote.gws.GwsSoap;
import jakarta.annotation.Nullable;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.cxf.transports.http.configuration.ProxyServerType;
import org.tempuri.Service;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import java.net.URL;

public enum GwsClientServiceFactory {
    ;

    public static GwsSoap createGwsClientService(@Nullable GwsClientServiceProxyConfig proxyConfig,
                                                 GwsClientServiceConnectionConfig connectionConfig)
    {
        URL wsdlLocation = Thread.currentThread().getContextClassLoader().getResource("wsdl/tab_nsw.wsdl");
        if (wsdlLocation == null) throw new RuntimeException("WSDL not found on classpath at wsdl/tab_nsw.wsdl");
        QName serviceName = new QName("http://gws.amtote.com/", "gws");
        Gws gws = new Gws(wsdlLocation, serviceName);
        GwsSoap gwsSoap = gws.getGwsSoap();
        Client client = ClientProxy.getClient(gwsSoap);
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        if (proxyConfig != null) {
            HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
            httpClientPolicy.setProxyServer(proxyConfig.proxyServer());
            httpClientPolicy.setProxyServerPort(proxyConfig.proxyServerPort());
            httpClientPolicy.setProxyServerType(ProxyServerType.HTTP);
            conduit.setClient(httpClientPolicy);
        }
        AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
        authorizationPolicy.setUserName(connectionConfig.username());
        authorizationPolicy.setPassword(connectionConfig.password());
        conduit.setAuthorization(authorizationPolicy);
        BindingProvider bp = (BindingProvider) gwsSoap;
        String endpoint = connectionConfig.baseEndpoint();
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        return gwsSoap;
    }

    public static IService createIService(@Nullable GwsClientServiceProxyConfig proxyConfig,
                                          GwsClientServiceConnectionConfig connectionConfig)
    {
        URL wsdlLocation = Thread.currentThread().getContextClassLoader().getResource("wsdl/main.wsdl");
        if (wsdlLocation == null) throw new RuntimeException("WSDL not found on classpath at wsdl/main.wsdl");
        QName serviceName = new QName("http://tempuri.org/", "Service");
        Service service = new Service(wsdlLocation, serviceName);
        IService iService = service.getBasicHttpBindingIService();
        Client client = ClientProxy.getClient(iService);
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        if (proxyConfig != null) {
            HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
            httpClientPolicy.setProxyServer(proxyConfig.proxyServer());
            httpClientPolicy.setProxyServerPort(proxyConfig.proxyServerPort());
            httpClientPolicy.setProxyServerType(ProxyServerType.HTTP);
            conduit.setClient(httpClientPolicy);
        }
        AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
        authorizationPolicy.setUserName(connectionConfig.username());
        authorizationPolicy.setPassword(connectionConfig.password());
        conduit.setAuthorization(authorizationPolicy);
        BindingProvider bp = (BindingProvider) iService;
        String endpoint = connectionConfig.baseEndpoint();
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        return iService;
    }
}
