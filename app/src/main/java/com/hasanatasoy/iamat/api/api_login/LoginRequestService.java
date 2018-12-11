package com.hasanatasoy.iamat.api.api_login;

import com.hasanatasoy.iamat.CurrentUsername;
import com.hasanatasoy.iamat.api.NetworkService;
import com.hasanatasoy.iamat.login.LoginDTO;
import com.hasanatasoy.iamat.login.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRequestService {

    LoginRequest request = NetworkService.getRequestApiLogin();
    private LoginView view;
    public String username;

    public LoginRequestService(LoginView view){
        this.view = view;
    }

    public void doLoginCheck(LoginDTO loginDTO){
        username = loginDTO.getUsername();
        try {
            request
                    .doLoginCheck(loginDTO)
                    .enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.body()){
                                view.showSuccess("Giriş Başarılı");
                                view.doLogin();
                                view.assignCurrentUsername(username);
                            }
                            else {
                                view.showError("Bağlantı Hatası");
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            view.showError("Bağlantı Hatası");
                        }
                    });
        }
        catch (Exception e){
        }
    }


}
