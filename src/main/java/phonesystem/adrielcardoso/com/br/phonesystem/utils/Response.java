package phonesystem.adrielcardoso.com.br.phonesystem.utils;

public class Response
{
    private Object data;
    private String mensagem;
    private int status;

    public Response(String mensagem, int status, Object data) {
        this.mensagem = mensagem;
        this.status = status;
        this.data = data;
    }

    public Response(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
