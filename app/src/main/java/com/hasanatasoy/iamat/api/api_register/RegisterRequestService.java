package com.hasanatasoy.iamat.api.api_register;


import com.hasanatasoy.iamat.api.NetworkService;
import com.hasanatasoy.iamat.register.RegisterDTO;
import com.hasanatasoy.iamat.register.RegisterView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRequestService {

    private RegisterRequest registerRequest = NetworkService.getRequestApiRegister();
    private RegisterView view;

    public RegisterRequestService(RegisterView view){
        this.view = view;
    }

    public void doRegister(final RegisterDTO registerDTO){
        try {
            registerRequest
                    .doRegister(registerDTO)
                    .enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.body()){
                                view.showSuccess("Kayıt Başarılı");
                                view.goBackLoginScreen();
                            }
                            else {
                                view.showError("Kayıt Başarısız Tekrar Deneyin");
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            view.showError("Kayıt Başarısız Tekrar Deneyin");
                        }
                    });
        }
        catch (Exception e){

        }

    }
}
