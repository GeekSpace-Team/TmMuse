package geek.space.tmmuse.API;

import geek.space.tmmuse.Model.AboutUs.Constant;
import geek.space.tmmuse.Model.AllProfile.GetProfile;
import geek.space.tmmuse.Model.AllProfile.GetProfileTiny;
import geek.space.tmmuse.Model.AllProfile.ResponseAllProfile;
import geek.space.tmmuse.Model.Certificate.InsertCertificate;
import geek.space.tmmuse.Model.Film.BronMovie;
import geek.space.tmmuse.Model.Film.BronMovieUsersBody;
import geek.space.tmmuse.Model.Film.CancelBronFilm;
import geek.space.tmmuse.Model.Film.RequestBronFilm;
import geek.space.tmmuse.Model.GetCard.GetCardResponse;
import geek.space.tmmuse.Model.GetCard.PostGetCard;
import geek.space.tmmuse.Model.GetCard.SendGetCard;
import geek.space.tmmuse.Model.GetPromoCode.GetPromoCodeBody;
import geek.space.tmmuse.Model.GetPromoCode.GetPromoCodes;
import geek.space.tmmuse.Model.Help.PostHelp;
import geek.space.tmmuse.Model.Home.Home;
import geek.space.tmmuse.Model.Interest.GetInterest;
import geek.space.tmmuse.Model.Interest.PostInterest;
import geek.space.tmmuse.Model.LikeDislike.PostLikeDislike;
import geek.space.tmmuse.Model.Message.GetMessage;
import geek.space.tmmuse.Model.SearchHistory.GetSearchHistory;
import geek.space.tmmuse.Model.SearchHistory.SearchHistory;
import geek.space.tmmuse.Model.SearchPage.PostSearchProfile;
import geek.space.tmmuse.Model.UserRegister.CheckUserCode;
import geek.space.tmmuse.Model.UserRegister.ResponseCheckUser;
import geek.space.tmmuse.Model.UserRegister.StringResponse;
import geek.space.tmmuse.Model.UserRegister.UserPostRegister;
import geek.space.tmmuse.Model.ViewCound.AddViewCount;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/get-home?")
    Call<Home> getHome(@Query("page") Integer page);

    @POST("/phone-verification")
    Call<StringResponse> phoneVerification(@Body UserPostRegister userPostRegister);

    @POST("/code-verification")
    Call<ResponseCheckUser> code_verification(@Body CheckUserCode checkUserCode);

    @GET("/get-interest")
    Call<GetInterest> get_interest(@Header("Authorization") String token);

    @POST("add-user-interest")
    Call<StringResponse> add_user_interest(@Body PostInterest postInterest,
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

    @GET("/get-card-promotion?")
    Call<GetCardResponse> get_card_promotion(@Query("limit") Integer limit,
                                             @Query("page") Integer page);

    @GET("/get-search-history")
    Call<GetSearchHistory> get_search_history();

    @POST("/search-profile")
    Call<PostSearchProfile> search_profile(@Body SearchHistory body);

    // Response
    @POST("/insert-inbox")
    Call<StringResponse> insert_inbox(@Body PostHelp postHelp, @Header("Authorization") String token);

    @GET("/answers")
    Call<GetMessage> answer(@Header("Authorization") String token);

    @POST("/add-ticket")
    Call<RequestBronFilm> add_ticket(@Body BronMovie body, @Header("Authorization") String token);

    @GET("/get-current-ticket?")
    Call<BronMovieUsersBody> get_current_ticket(@Query("user_id") String user_id,
                                                @Header("Authorization") String token);

    @POST("/get-promo-codes")
    Call<GetPromoCodes> get_promo_codes(@Header("Authorization") String token,
                                        @Body GetPromoCodeBody profile_id);

    @PUT("/ticket-status-update?")
    Call<ResponseBody> ticket_status_update(@Header("Authorization") String token,
                                            @Query("id") Integer id,
                                            @Body CancelBronFilm cancelBronFilm);

    @POST("/add-like-dislike")
    Call<ResponseBody> add_like_dislike(@Body PostLikeDislike postLikeDislike);

    @POST("/create-certificate")
    Call<ResponseBody> create_certificate(@Header("Authorization") String token,
                                          @Body InsertCertificate insertCertificate);

    @POST("/add-view-count")
    Call<ResponseBody> add_view_count(@Header("Authorization") String token,
                                      @Body AddViewCount addViewCount);

    @POST("/add-click-count")
    Call<ResponseBody> add_click_count(@Header("Authorization") String token,
                                       @Body AddViewCount addViewCount);
}
