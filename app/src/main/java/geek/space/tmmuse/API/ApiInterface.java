package geek.space.tmmuse.API;

import geek.space.tmmuse.Model.AboutUs.Constant;
import geek.space.tmmuse.Model.AllProfile.GetProfileTiny;
import geek.space.tmmuse.Model.AllProfile.ResponseAllProfile;
import geek.space.tmmuse.Model.AllProfile.GetProfile;
import geek.space.tmmuse.Model.GetCard.PostGetCard;
import geek.space.tmmuse.Model.GetCard.SendGetCard;
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

    @GET("/get-constant?")
    Call<Constant> get_constant(@Query("type") String string);

    @POST("/get-profile")
    Call<ResponseAllProfile> get_profile(@Body GetProfile getProfile);

    @GET("get-profile-tiny?")
    Call<GetProfileTiny> get_profile_tiny(@Query("profile_id") String profile_id);

    @POST("create-card-user")
    Call<PostGetCard> create_card_user(@Body SendGetCard sendGetCard,
                                       @Header("Authorization") String token);


}
