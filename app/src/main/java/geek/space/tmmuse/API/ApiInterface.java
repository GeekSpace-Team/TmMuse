package geek.space.tmmuse.API;

import geek.space.tmmuse.Common.Utils;
import geek.space.tmmuse.Model.Home.Home;
import geek.space.tmmuse.Model.Interest.GetInterest;
import geek.space.tmmuse.Model.Interest.PostInterest;
import geek.space.tmmuse.Model.UserRegister.CheckUserCode;
import geek.space.tmmuse.Model.UserRegister.ResponseCheckUser;
import geek.space.tmmuse.Model.UserRegister.UserGetRegister;
import geek.space.tmmuse.Model.UserRegister.UserPostRegister;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/get-home?")
    Call<Home> getHome(@Query("page") Integer page);

    @POST("/phone-verification")
    Call<UserGetRegister> phoneVerification(@Body UserPostRegister userPostRegister);

    @POST("/code-verification")
    Call<ResponseCheckUser> code_verification(@Body CheckUserCode checkUserCode);

    @GET("/get-interest")
    Call<GetInterest> get_interest(@Header("Authorization") String token);

    @POST("add-user-interest")
    Call<UserGetRegister> add_user_interest(@Body PostInterest postInterest,
                                         @Header("Authorization") String token);

}
