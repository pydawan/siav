package br.gov.go.agr.siav;

import java.io.IOException;

import org.apache.directory.api.ldap.model.cursor.CursorException;
import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.BindRequest;
import org.apache.directory.api.ldap.model.message.BindRequestImpl;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;

import org.junit.Test;

/**
 * <p>
 * Teste de integração entre a linguagem Java e 
 * o protocolo de aplicação LDAP.
 * </p>
 * 
 * @author thiago
 * @version 1.0
 */
public class ActiveDirectoryTest {

    /**
     * Pesquisa por todos os usuários na base de dados do Active Directory.
     * 
     * @author thiago
     * @version 1.0
     */
    @Test
    public void testConexao1() {
        LdapConnection connection = new LdapNetworkConnection("10.243.1.5", 389);
        try {
            connection.bind("CN=aplicacao,OU=proxy,DC=agr,DC=go", "agr2015");
            EntryCursor cursor = connection.search(
                "OU=agr,DC=agr,DC=go", 
                "(objectClass=user)", 
                SearchScope.SUBTREE, 
                "*"
            );
            while (cursor.next()) {
                Entry entry = cursor.get();
                System.out.println(entry);
                Thread.sleep(1000);
            }
            connection.unBind();
            connection.close();
        } catch (LdapException e) {
            e.printStackTrace();
        } catch (CursorException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pesquisa pelo usuário thiago-amm na base de dados do Active Directory.
     * 
     * @author thiago
     * @version 1.0
     */
    @Test
    public void testConexao2() {
        LdapConnection connection = new LdapNetworkConnection("10.243.1.5", 389);
        try {
            connection.bind("CN=aplicacao,OU=proxy,DC=agr,DC=go", "agr2015");
            EntryCursor cursor = connection.search(
                "OU=agr,DC=agr,DC=go", 
                "(&(objectClass=user)(sAMAccountName=thiago-amm))", 
                SearchScope.SUBTREE, 
                "*"
            );
            while (cursor.next()) {
                Entry entry = cursor.get();
                System.out.println(entry);
                Thread.sleep(1000);
            }
            connection.unBind();
            connection.close();
        } catch (LdapException e) {
            e.printStackTrace();
        } catch (CursorException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConexao3() {
        LdapConnection connection = new LdapNetworkConnection("10.243.1.5", 389);
        try {
            connection.bind("CN=Thiago Alexandre Martins Monteiro,OU=SUPI,OU=GEGPLAN,OU=AGR,DC=agr,DC=go", "senha");
            EntryCursor cursor = connection.search(
                "OU=agr,DC=agr,DC=go", 
                "(&(objectClass=user)(sAMAccountName=thiago-amm))", 
                SearchScope.SUBTREE, 
                "*"
            );
            while (cursor.next()) {
                Entry entry = cursor.get();
                System.out.println(entry);
                Thread.sleep(1000);
            }
            connection.unBind();
            connection.close();
        } catch (LdapException e) {
            e.printStackTrace();
        } catch (CursorException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConexao4() {
        LdapConnection connection = new LdapNetworkConnection("10.243.1.5", 389);
        try {
            BindRequest request = new BindRequestImpl();
            request.setSimple(true);
            request.setName("thiago-amm@agr.go");
            request.setCredentials("senha");
            connection.bind(request);
            EntryCursor cursor = connection.search(
                "OU=agr,DC=agr,DC=go", 
                "(&(objectClass=user)(sAMAccountName=thiago-amm))", 
                SearchScope.SUBTREE, 
                "*"
            );
            while (cursor.next()) {
                Entry entry = cursor.get();
                System.out.println(entry);
                Thread.sleep(1000);
            }
            connection.unBind();
            connection.close();
        } catch (LdapException e) {
            e.printStackTrace();
        } catch (CursorException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
