package api;

import servlets.BaseServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/v2/accounts/types")
public class AccountTypes extends BaseServlet {

    @Override
    protected String[] getAllowedMethods() {
        return new String[] { GET, OPTIONS };
    }
}
