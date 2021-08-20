/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

//exceção para evitar que o usuario deixe vazio o input
public class CampoVazioException extends Exception {

    public CampoVazioException() {
    }
    public CampoVazioException(String msg) {
        super(msg);
    }
}
