/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ip.monitoring.config;

/**
 *
 * @author uhitlei.barbosa
 */
public class configDTO {
  
  private Integer server; //0-  Gmail, 1- Outro
  private String  smtpUrl;
  private String  smtpPorta;
  private String  loginEmail;
  private String  senhaEmail;
  private Boolean serverProxOn;
  private String  proxyHost;
  private String  proxyPorta;
  private String  proxyUsuario;
  private String  proxySenha;
  private String  emailDestino;
  private String  emailAssunto;
  private String  ultimoIP;
  private String  tempoMin;

  /**
   * @return the server
   */
  public Integer getServer() {
    return server;
  }

  /**
   * @param server the server to set
   */
  public void setServer(Integer server) {
    this.server = server;
  }

  /**
   * @return the smtpUrl
   */
  public String getSmtpUrl() {
    return smtpUrl;
  }

  /**
   * @param smtpUrl the smtpUrl to set
   */
  public void setSmtpUrl(String smtpUrl) {
    this.smtpUrl = smtpUrl;
  }

  /**
   * @return the smtpPorta
   */
  public String getSmtpPorta() {
    return smtpPorta;
  }

  /**
   * @param smtpPorta the smtpPorta to set
   */
  public void setSmtpPorta(String smtpPorta) {
    this.smtpPorta = smtpPorta;
  }

  /**
   * @return the loginEmail
   */
  public String getLoginEmail() {
    return loginEmail;
  }

  /**
   * @param loginEmail the loginEmail to set
   */
  public void setLoginEmail(String loginEmail) {
    this.loginEmail = loginEmail;
  }

  /**
   * @return the senhaEmail
   */
  public String getSenhaEmail() {
    return senhaEmail;
  }

  /**
   * @param senhaEmail the senhaEmail to set
   */
  public void setSenhaEmail(String senhaEmail) {
    this.senhaEmail = senhaEmail;
  }

  /**
   * @return the serverProxOn
   */
  public Boolean getServerProxOn() {
    return serverProxOn;
  }

  /**
   * @param serverProxOn the serverProxOn to set
   */
  public void setServerProxOn(Boolean serverProxOn) {
    this.serverProxOn = serverProxOn;
  }

  /**
   * @return the proxyHost
   */
  public String getProxyHost() {
    return proxyHost;
  }

  /**
   * @param proxyHost the proxyHost to set
   */
  public void setProxyHost(String proxyHost) {
    this.proxyHost = proxyHost;
  }

  /**
   * @return the proxyPorta
   */
  public String getProxyPorta() {
    return proxyPorta;
  }

  /**
   * @param proxyPorta the proxyPorta to set
   */
  public void setProxyPorta(String proxyPorta) {
    this.proxyPorta = proxyPorta;
  }

  /**
   * @return the proxyUsuario
   */
  public String getProxyUsuario() {
    return proxyUsuario;
  }

  /**
   * @param proxyUsuario the proxyUsuario to set
   */
  public void setProxyUsuario(String proxyUsuario) {
    this.proxyUsuario = proxyUsuario;
  }

  /**
   * @return the proxySenha
   */
  public String getProxySenha() {
    return proxySenha;
  }

  /**
   * @param proxySenha the proxySenha to set
   */
  public void setProxySenha(String proxySenha) {
    this.proxySenha = proxySenha;
  }

  /**
   * @return the emailDestino
   */
  public String getEmailDestino() {
    return emailDestino;
  }

  /**
   * @param emailDestino the emailDestino to set
   */
  public void setEmailDestino(String emailDestino) {
    this.emailDestino = emailDestino;
  }

  /**
   * @return the emailAssunto
   */
  public String getEmailAssunto() {
    return emailAssunto;
  }

  /**
   * @param emailAssunto the emailAssunto to set
   */
  public void setEmailAssunto(String emailAssunto) {
    this.emailAssunto = emailAssunto;
  }

  /**
   * @return the ultimoIP
   */
  public String getUltimoIP() {
    return ultimoIP;
  }

  /**
   * @param ultimoIP the ultimoIP to set
   */
  public void setUltimoIP(String ultimoIP) {
    this.ultimoIP = ultimoIP;
  }

  /**
   * @return the tempoMin
   */
  public String getTempoMin() {
    return tempoMin;
  }

  /**
   * @param tempoMin the tempoMin to set
   */
  public void setTempoMin(String tempoMin) {
    this.tempoMin = tempoMin;
  }

  
  
  
}
