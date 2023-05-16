package fmgp.did.demo

import zio._
import zio.json._

import fmgp.did._
import fmgp.did.method.peer._
import fmgp.crypto._

/** didExampleJVM/console
  */
object Agent0Mediators { // https://localhost:8080/
  // "did:peer:2.Ez6LSpou63sBDB4FGpbVM23bECgZnkMHj6hGmA3PgQByR9fs4.Vz6MkhNpHBCUgBgkCbiM4zMjrbfgGowwEuEchmzf6J5W3av8E"

  val agent = DIDPeer2.makeAgent(
    Seq(keyAgreement, keyAuthentication),
    Seq(DIDPeerServiceEncoded("https://localhost:8080/"))
  )
  def agentLayer: ULayer[Agent] = ZLayer.succeed(agent)
  def keyAgreement =
    OKPPrivateKey(
      kty = KTY.OKP,
      crv = Curve.X25519,
      d = "b6JxUFsuwKPkOUAvU4FXEYzC7oKhzwNOM1aWyfvHP7k",
      x = "wyJl5uFCb4OXE_KuRePrM92z6aPfk8PXBHIpg1rG528",
      kid = None
    )
  def keyAuthentication =
    OKPPrivateKey(
      kty = KTY.OKP,
      crv = Curve.Ed25519,
      d = "i6gfpmHFrSHbwAa7gI-bnOL0gMyePZ6Pe1xN-TDPMO4",
      x = "K2-apfExODENs4ZGlZ2xh8DSq4vkCqG3fgS3Ofwslv8",
      kid = None
    )

}

//curl -X POST localhost:8080 -H 'content-type: application/didcomm-encrypted+json' -d '{"ciphertext":"Fi2jLme4UH_9ShS-krILTIFp9DQh5WLZKDbrWUvTUBN_eUZVfzu8UpwQ4_uiLfyOmEETQyUaw4Gar11c6xn9B4H3cBxKs6OiyndUKmqnNQdZnPZ1gxEDGY5Vv6_NArOr6s14Ae2b9-R9RJuMnhw1movBlZrQ8dH00jK1AgaPk73P9Bj6SH37xn4F139-VuVlcB-XL2O5NLE9lG-P05IUFoswxQuk8irFilG45dA4xBzuTl83er2w1DqH--2Cg08XCXOOFdCwL8NwbLwWNTCNRJl7nZ9BhON_PkkQZEojiUYuEC5VBLWV7SwmZp4HnvSaISXn7RXBdqWukBytSe1SaOSaDGuYdOJH9FlHXy41JXxd4GxtLTmpmPIp0qRUb9rGN0hkz8zb-rX6jrpn5DDRSJVmDNuTr-qgwLxNv9ZkTCrdH4QzTILJnOcpGKZMrtzy2SulFQhSztig52ERqEVxIUDL79YlzehYG2PhiNudLy_-7zt94usohl9YpZQVDqLlbKa-x3pRYRKQJkKuw32_A6VLg54Ral5lXBgkF-llUkP6YxI9wpaLUGFMwm5W7W5AD__N33XmQcIiYWkrfJbfLVIv30cbDXvRsiRjnZWSTb6YwrUNdn7qs0riPqHVG3zk0YfldG7Ee-EbTVTk1b6tAkYAyevQGseSYcFha9SyLCrLQ6xSEjVZ7fgrVtQO-n6dbiZityLDA59w3ietNDzjePysImmRcudWd5ok79abnn3zs9eENU9DDI8FL8LdqbhtYG-MUKICkR0foYPsOUmxxacdy5O6AhdA4XTAvAig6QoF83wFMtRcDKiE5O38d_1XWNIx_XXt8DNIQFbWOJJ-z8h2qebcJDXQEUs0CFMEdX-zqfXulTz6JgHCwWMnBqG8N5ichGYJ-T-R3CaJ3_WGwcqPYIHBaYT8cbtJVEVfTlg0T6dGt7kJPO1P9TS6MVBCIbFSJysEAV1-xHqyPh9ZFCfTrC87mVnC01izyKYgY8ybCcxr3UWm40u2H8NG8APHyJmjgwxun29eI_EJ02iC_-PAa_6kgjw9sfkLk08e96vWsvoaqLen-JdtYTtSoObqwQWk405Xv75UCvU4hT1ydnFeMONTHVtjrLV3jGGVpCSlJHYyvN9VRVCTFOrMJ4iL-OgQjk04kFMECRMO6uFRHho0nhR1Nvlj5BaaZuqIOFSAuGm5hYjFYuHiQaFCboYzpEd4mVw0lFtJRcZWlmVx_q_YGNazv4kure5Wo0NBzcAPlUxYMMhXaMau7M8qutK-yBXxXFZnLxdly10dOmyNTHjREvHUzqAbjltr1n5V0_rbF69FxF8E_WUO8pDlz9A63VaU_EdLuBJ0jgZ3Bhe3zDcDNKVL7fDfKIA2xoMH1BdbSxKCJ7rel6ohe1bNO8v_S6mva149lM-i9WlgywtGIeriZvt24cAwmVD2REu44IRQm6o92ARAWTAjEff00JN7LiXzAfw_XskRnut9sc1mZIFw0dh7lhdkLhOh-DGb1MDkCf86_G9R5U2xFNGxRPfJ_-T9m442sgyRoEZmJsPmn0rUaLlljmyuMaf7TCW7f_6X1lc9elhyKU9At4oMDqZ2IPA4iyoAYIcxlWYWAZRwoBMC2ZT8aQxThii5J8h85dIXAzVbUbSOU17k9YfyR08Afd7Jpj6xOi3PfwDztIatDtefwOrOaWmDt2p6yvx22E68X7yn9mZOuR2KY_5ksbuJsKcrXVI0zk8r8cAIlfiGLOnIXlGvaB2fkf2dw3B-wc7iTy4IUlU7U58XmBrAk-RekSIAVgKNn-iUQGuq_fHAJi1aeAnN_1WR8QE-1d8EWIiw6a08vfWE0p-Pw33V-b-vW2jrnMpg9U93liY1lQFDjdyGV-2rNZw-rB7ei3AOaD0jgWNVO2ABknz4Lu7qnh8d3bs-nRMlkU1yxQnFtBPqXiT5_prVz70ow_XKVa-5MdBdLP-SwXFSbIkubG1FmZr2maIQQBTQ37q6FNBV2Rn_K-yGvvV5OrEM72bOoJmbebilrxlsJ0-6GG1Lt9Qa2mSserStg2oxs62niCJsCrOM9KlTfQatNM8FtFlEmz9nCuLm6cmM6Ulv7mdqwj0fxbUf2PjkMs2CbpUHyr7P0BJkfqkm8bcBYAK1IevF8J3RZBjt9ulGPYdHOJtkx6jk8k44wkDacL4gsbUhondwmRTl1fXp2olXX5j6saXFlVrPQ-r1q-ZB2cPDh6JFYpMJfswWLDZ8b2FznBhhTZhkIWJ6EbJS_qHjc-RfoLVVVA_MguitVunOPFm58OH6HT9Tk6y4Hk3z-E0pIubm4vLqvF7H8M-ThAv7f49xGP7Fdd0rnadRdImDw6Tt6PjZRPKOvR3V1FL5XK-alZISuwT6HMEGvnDGi0e6MJTd-FPSvEyUaW_7CIILP0K27834tTgmiNtDeIuV-KuxI2tE-Tgrr1Gd9u2cmz5NywcVOvgRzYP39aqs0nE3OipKjIl6N84lGn5GV4CDy8Mlmh3_DU0Y2yhkzw6hPPZxiSzxnjZ0dLHolUUZlREbTt6S5UaTIU6S4QpLEcLajur4eZ6usFLTiOVmeZetglls-2R06MhaHGzzLPgaS7zBfFd9cCkxS9UWm3Z6i9uz-7GrbcCQbmCiqMWp-1iCSVwqSqPcEB_PRNsbOVrixQIo7ZTcNqlqLsgBK_1A0gpQoOXbqiwDTsdxwFMehp9Cr1g2S-aw0UNSj8ujvfA_9zSBJvxZq9hxNMJx-qSrk4T9XlhTBDV-Z8uBGojaDfKM4hXtFpnoZ1zeGaiUBuQxugYT12-lKkp0WXxn6yyqBkWpyt0uxpZmQkVUN_7rGKlc7FymyX1mRt3ftFLTnwohls704aqo6Dtg2m9Rd0xeiPca9gVGQEQDzm2ZCmYMgBlJl760wBejNguR3X_xb-t9ex_KuicgT8k9-NyaJSVI0UKKLFatQXsLA-yAGeiRe_A2HfVrkBwbzjR056AaNYo-EEdMprXBa-gY8WR-L-iB0qLu7tsoHQze6Qsp-HJx2tvKjSnHu23oxs876ngA9bhE932d0ALT4nOV0uPEMrTFxevOEecpWtt6MG973ixc4RtZ6-OL-AS_QJ32_VkB9qaN-88mfF9WVEHtAgmhixVz93ohAfFl6lNadsMZJweHI73KB5oGDtSMZR21QQ1IcZNMtGmG1EiSvkIf7gYua8rQylKuiWGdnioCpYnxO2Wq4knyW2SMOP4KTDBs610GZkDcvblc62s0sOP15D4GX0J2Pk96JPWb_SkqZkcKgHYjoD0-HzZ9nnonnwkqp8gQRrXzFO1lF0vXjRTvHtQtmi8FY4vjQPeL7VILteoMA4HW8b5zzwn6BdsjDYbGfvdqYQykDaYU_zt-_obpDYsErNzNP-1pcZ0llCnpHdaK-xDjdA-xPPMyJmYB4Zc3Ut073o3TsQFrOcYmlN6d40A5x5F7JaOxpao7iomvWk1sq9gGIzXxXGCUqc7utVHPmWhc4ADUHPUHH-n5G0LdlbkdziNEICkMwAsL7rLIbLHLR5cK4GhYqO2PgpObhkwpcRG5OeqtopFUnv-it5-z4TmqwDUdzP3XH3rGlfzDIHX5xDNG6dBbCykcWWyptCxvUsT_oAnKiZAfR7xscn5PMIT5dgxTupzkzlot84wvoGgOq8CB-ClDmrHjzw4XO6iXUKQp6r3Tu34KYs8ttrS-9GF3H1EdQy7IoMBwCdPTd9v74hTIo3F9y8s2VL3KWuwVK_jI5sW5s75_AyqfN7I7l2KlgxgchJcrdNwuv3Q6rDRAsQ7CaixTlrKPsnX6RfkjLkf3vsMJDxGzKWzQ70Ae3PAfTxT0BRhxajweZiRA8pXDYW2cf-1QCKD4V_zrxScPZ3jtQguAVUBnQxDEDnxC3kwKRSU4rNtlcfU8dmHrRgP7ChKJxMqyLkOkHXQWChYlqq1qtksLgy3iOVrgnU5RGJzL9EH3-sBEA20PeRGHV81w_WYsF1qckvJ0p9xmt03Rkc6I2UpRSbPxZrkgdBLV3cGWKdwsVSWj54X2Df07u1_CZi7TDxNJMX-KRsoTd4owuEOP_PTBkGAuv20Rh9vN-Li9UvE2qQlRL5HwRe8JH4mV-JB8YbtEJN-rKF3swmtobmzlbV01zZG3BpZmBshHJ3DpmeRYfQFps8O04maWG8Q-zqvfDwe3GHNSTz2r4kx1vj0p8Tx09-d5ldSuo80Oak4qyr9PuEXAMDpi1_A3DtBGkBwGo8sfRoqX0GHqXgO61EChyhg8DmEHm1mSm0WhjieF2zyEu-zHgRtHFOiRnRn7-lgBzmhbpDRfGC9DwsxGIr6hR3YL11affDtDfmXyUD9fb3iayLJCqeaLt9m8d3UwDDrtK0a_dGhXryF9vLfjXMyKZO4HFeU1YY2FAgO_m_XpzelXF3ZSFf99VsI3BWm9YWiZ0S4DK7eLKQq6ohRXwMmfb_QuJELsqjJFzYKJpjfUOwS3mgAa8PGZEi9yy0Q-eb3EidUQMfcLIr2xrAp1gq46aqPgy6v2d47m8SPPvtwdHJDsUaR-rZhYVSva0ZbRUuFwATMcZvsoI4euHzwXvhj8Tbfb3bg9wIPC3w3c3K6ptVfyl3usP5MSW6OxTYlhqJ1e4l3CBm5bnmaoaVuBN9L62slHzxhvx5jWhfx-zjgUw8owKABxUW9ZQ9-pjLNDpexWvdl8LNNU3GXj-wDiYjIhiXAra344MjYE8DZuOTHL0VtHe1diygLt46WGnM841eZcg-YqZaBPZsHJT3NPR-cyc1sUeLrC_5oIzQS8Yi2C4YcYTQ96rQv8Mlx70MxZKPE_fm6RGP7v2EHH9hzD6w2MIdpFl_CIUM7yJZ4b2d-XraQZ34zdnTZAuRoEeC0UATCmBLH0qnZ6Mi-uepXvKBgy8VglLkqwMBi19FGc49p6TLB412PNq3p7vxdu86D_NYout-XJcoSxZEl_h3uhDJzdF3VPJjGbKIZs52fFROiYedSqD8jQZH2zUJvFXlbil8Vo-Jq4Jpbkn6z4nCwn-GVQJs_QfknR-lFVrA2gCYGLr0y6EQC-S8PO24naFiqWWfXCLAwuQ-ExiQJ2plTP7BQtYrj0AlqO6KZG8l2fan0tsjm6rztY7rPPJJj0gB5JAAguUGtWnHSAU8Knsb3bP7TybW6q8fRz_AOEv-mYSVTUwtkvh6yBLi3kVBIUrhdqfGU7cI-yoicRWu_Z4Cb0pYiwjpRfHyVBnFQICIKPPHQThVrpuSdcOK-BkVc85vlqS8zA-MXF3QBOgCE0pQB114nsncvw4T1WfzniDahMg6VQ3eTWtcYYj30m7SNuSOuu4u7yI8asDBkAM_NPVBl-x1Mhh8CQMiMHZG_dlVA7-RNlAFSshn_0vcInrRTwJ_lyKZLcuaaakabx4R7prY5WknOU28hw0hafFDuNblIwuEcLGE6YTaevatUAVdzDlE1AhFZV9d6jEQ5Bxc-yP-Fge3nGGE-lnezby8LVcG5X0bQzYmr8XtCaQJnGVgfOCISwaQhVtgVbXd2uGc2AvRLOAo6BH0Qrz55RQWc1iOhUX9jzwg06j4HBxpxAZ6MHThtN1lGrEtVmycDsg6pXeI4aLy4pWPqaqnQ_oOP9O6kaIk4Exvizni-tqqCpnLgfc4kAfHBgow","protected":"eyJlcGsiOnsia3R5IjoiT0tQIiwiY3J2IjoiWDI1NTE5IiwieCI6InlnbnhONHY4QXZZWjFPbTZhVmdRUlp3eWM5UGRNT3ZMZmd3Uk1YVHhjMW8ifSwiYXB2IjoiMlVTQkdqWG9PaWkzbFA5cjBGRERvTjlZTEkxbjZPNDAzTzZTbHcteHZVayIsInNraWQiOiJkaWQ6cGVlcjoyLkV6NkxTa0t3MW1va1puRWlFbkFLQ0tRQzl0NVlLaGExZGl2M1VmRVRDOUZBc1R1YjEuVno2TWtwSlVjZDJ6VHM1c3VyWHBWUjRETGVnd0V6MXBUSlBMcXRUaFVIWmdXQzI0UCM2TFNrS3cxbW9rWm5FaUVuQUtDS1FDOXQ1WUtoYTFkaXYzVWZFVEM5RkFzVHViMSIsImFwdSI6IlpHbGtPbkJsWlhJNk1pNUZlalpNVTJ0TGR6RnRiMnRhYmtWcFJXNUJTME5MVVVNNWREVlpTMmhoTVdScGRqTlZaa1ZVUXpsR1FYTlVkV0l4TGxaNk5rMXJjRXBWWTJReWVsUnpOWE4xY2xod1ZsSTBSRXhsWjNkRmVqRndWRXBRVEhGMFZHaFZTRnBuVjBNeU5GQWpOa3hUYTB0M01XMXZhMXB1UldsRmJrRkxRMHRSUXpsME5WbExhR0V4WkdsMk0xVm1SVlJET1VaQmMxUjFZakUiLCJ0eXAiOiJhcHBsaWNhdGlvblwvZGlkY29tbS1lbmNyeXB0ZWQranNvbiIsImVuYyI6IkEyNTZDQkMtSFM1MTIiLCJhbGciOiJFQ0RILTFQVStBMjU2S1cifQ","recipients":[{"encrypted_key":"ydirq3D3UiQ4jF2eWigdDNeNGEyNYxvnW8n8nqxmi_KjotdWhM1klF59beFIQlgerXJRVnG1ywfaDUR1wckZd4SfeUCPbSwd","header":{"kid":"did:peer:2.Ez6LSpou63sBDB4FGpbVM23bECgZnkMHj6hGmA3PgQByR9fs4.Vz6MkhNpHBCUgBgkCbiM4zMjrbfgGowwEuEchmzf6J5W3av8E.SeyJ0IjoiZG0iLCJzIjoiaHR0cHM6Ly9sb2NhbGhvc3Q6OTA5MC8iLCJyIjpbXSwiYSI6WyJkaWRjb21tL3YyIl19#6LSpou63sBDB4FGpbVM23bECgZnkMHj6hGmA3PgQByR9fs4"}}],"tag":"BjWBrTT19UsW430RJUzZOUPbjudZsNk9TyIi2dBOMAY","iv":"UdSyBhbSxadCBGhaG6f1JQ"}' #Auth
//curl -X POST localhost:8080 -H 'content-type: application/didcomm-encrypted+json' -d '{"ciphertext":"v5eC0fdnxe2LZYJSbEv7xXbxbXZ63Romq3ZI7vz-eWUOHpGXaDoQhQjjgLU8ycdbDgDdLvoRLLoXlIchWWX4PbkV04wnZhOsMFXxwPr79QXPDQa28CXri0SSGz7bh5yhGcm_McVFwxVfDyUlVLr6rx7E8r6lrt2EnGhSRIWDdHq40SLtwWJA6kMstLnNzc3Xu6D39Edfadfh-96So1gJJ5ooKiFv5MZBQz7OP04qHXvucs4VSr-giF-8CStCkZ1yDTPOgvdsp7lD0wyyMMqT3dh-ur1aQfLnnqETqURL5xdXZIX0AKgTiWr3UZGNeGWG7rcEUaMp-nLkhmnFdrGv1lkKiFKQo9W8tblUZwZWrf7LlIleI17L84cLR5zhMy1NFMQbIphrLtVcGMPAY7wZA15UWfYNFWC54XQcKSwrarf-zVl6lil2saIjWd1Soa2IaqFZI0p4IJNDq-zJBHju4TAhHa-6R8HBvLUhJ0D7jOm37Xtr7y63qdgydET4MYNjmoiOXfQS1aden64voNwWXGAT-8UG6LP98Xo-g8kJvrFzTIC_sjL6b6PGF6BSDx0i-JXA6kS7fXTLZ1U0sfyo3_IyxMvqxJCD27rY0a8TJYUxl-n3dv-b-JAQUVQqwXmb8YKdVrz9MkKA-_SJx4l_JiUA6-yyey4aORvtqwtyJDzRlchfiHSykg1UN2GBWha5BuLRyigv98DitfraXiRaP7DwOoxcfpzy6r-liU1SF8j8qw_RNefJ0GQhrbT5oNsDNEmfH9HWcCIsMdsW7bG8esW6k9j2uF9BII6aP_enrnpmYQEPSS72BU9BnD2Dq8pzIEzU34QAHS8hO-IN8wLC7aMSNKWO0GZnMHqEQWGCiK4c_0HcAV9AJoqiHLRat4H34AdMjkUxQ-kcbuBdMATG5LTOmHiildQ7gsA0Hs9f_4DlD5NeVHSlgRCWfCv9LVbraGBLFiTikZ5FHewqOw5EeYka0pr1mCfvA1EhpDB0C5KlsDAonHEhqMqvHrbyCrzsZtUCYItC0H49Gu9LrB95Ln92Y1HmZ7ScRnxyBlGqlX1O-H0AKlkQ7cKQtKYI0fA9j9XGuyOeEkFXzGWfX6nwVUcU84KjgU61k1KDNPkk2BUbtaUmwHqvzIcYROTJwuoPAhp_p7mzvy7ntm3Ls0qEk67PyCddY7Pnhq-b6CPbyHauleDR5h7x1MliY-Q6jxFBlY4Pel0TydOZmMi7DOwnT3MTfwFK9dE7rJ4muwA6kfsThoN5N9SY3ExAdfIgeLEQc9m3Vd3yVPLeGZ5Ehy_z_iuTBU9DL08lnlSFa6m0dLgia23BvHXZt1sznrIqUQ0T6dT5-1Iy-V8lcfcl6r19eAZoT19VwpltMMrwijnjXkObU21RdgsP2hTeRJz-E9vyJjDFVFD-bE5RKlX_E_MmtC__Ogg1K9MPeMZd_f2zu0_3edujuIQuDmWCELFg7otshmsVvyV7FJkigOGCUasrf86BqAXyHaqczwdIEfhW7z4GrFcA2_j4-15m8DQn-SOvZDiDFPD2UFqX3t6fFBWVn7OVYrm85UBVuvogRlX0bJRCZ8QKOAzsutQTfbxG9Dm04L4vpp41rCBosUxSm_PxlPcCmErctbZax_Zj9PCJF80OJNqPb0TOUiYMh1q5jD1boeVZNcHuU1cBf-OhZdbYn0OivX0NTx1X4OhAMBxww99h59BzddpL7k8s7RxrlD5bQkgpJ3qJxfFjXMCLNk5plI5001S3uq1vxJIp5Ptf_-9Lx1C4Bn8QsGL9-y0-cp6BKi8Bdj4-dmuo-C6vsXyUcjHwpjlujRhMzgAZDbZ3xBudd9VZSMfJzJQye8Z2kkQzWFx8586XYi5qSLf7tdyJvc1dF2V7YHRrKVKL_tgZ6LcO368_wZlTCpRyFuJJMDh_YthYtTv1dWBf79Yec2jwxl3VJ02GZ07MIQKQwp9Cfo07-shcY6dywqfnI8aR5LuLOZEgEmr0OU1r6RdWHBI7Ii-w4-1OCOR3yaXggSpq8oXGLfk_E4jGu9G_2hh939YmuKUcAiV9bqb39a-88x4AJRqXvh41m89iakoYOFinfjvcP2EMZfPKhCSq7QOhd0CNNvrzYtT0AJmdQz7VX9zhqSjq72HCSC1jon7zGGp79As5V4JhtIqI12cmNRs66JZxvx6nD1WpaVVffzxklShVfdG403Clhy57LxDIBDDa_uGGwUWF1gGnJFu6JuA6flWg_-Mgc01a7z3XDaxGYNj_YhC32fyh0L6F4fCavt_1LVRYUV38qcI0IOyShpWS362nBd7Z1jbUsZ60HPiTKMseVrsZOeRxW0whJJ5fYhKbuD4MaLS07kE_lscn1M8dIfOgjbF1KsI7NykWMIJhOJ8H2o2d0OKuf9BZieHQF96O15jCJ3V1-LY__-eclDRhjG0a76oP8BR3Sq0lXfQlmGXD7xLUffvIBJDzoCDwW3jquMzDyo8WqOgwCsu0kKL0uvekd0SVzPQClWd4gvQwPGiULR9FW8ZFyShrsqIX3lJFrht1qZF_alhNcuw29JU0BLFQjYTecaZqAETg7YCuH9-Y6dgDYpDFmoMbqXo_uLWZtAybALNEkhJ1AAWTnJHxBcuxVw4P_pPkgWnhlwx1t7xvNKrPSLHFdbPilRlaMg9CA7C9dn5YKxqE3nQXH4sDDJ7snxEkzXRgbQsTGni9BS8WKOHULc-HyTvtObJNja0JYJTJtAiPUmJWp7CQH7qspPAeIr8iM8HRvBe4oiAplyU3R2Scn5DGWc4D1EE7siygofUTDUF3mlROhdzHqJhiLIBnCuCO3EFyIqgFpvN36JtRTrsrmUZ7sse43q9JE1ra-XvQigUAY_JFvd6-l-wQ3uuF5d_c0gA75FtcIHtoq10dmouhpsRb0aC9Ju3qFvDBFT2HK1YaFeEMKaxDawvyPMIhJwv2HOF5N6jGRYDj5msfSvIseiDeIQx3j4Gxas-oYfdAOfPuMmKcHj5QEHVxE0qOjp5RUHqKzv4DNinuDk8lCUYFI4nFcJ62rD_QNGPMvo0X17vA53lvQPl7hHLKHuqobed3t_yWJmNYRHmqmTl_7YLkdcvaHXvozDmw83gSmeKioKAQZGxpBAQYGiwmrdrOH8JZNAdfGSVQvFCNrQSvxvcLR07hfQ500xYZ4gXbdLaUUZvLPsevz3S58DVOY1sHb9406UjTB95zylbbIJDonnRNs0uJAaS238uwJZv-8U383uNeBtKS__b9TM7RGSGR1glHPxmN64CagHxTHcekikmti5dvelfxlfkvXXm8nR_n9TeOeXXth1_13xVrhLGR7SPg1xde6m3NgZeCycew-_D81IxyJ0pg_Yyqx76YTM_5ek1fqk7aTIdS-9wC0xHgLPhuvkC20d2vzlz01S2udfpURqfD2STneswIHYBaMba5ZDmLj-GG4byfXrbKOvryjTgZlQQCtRSD8LPNu7jjx7Sj37IWTuWGji8XsgRNijLecZ42zA1555VQqYhfx5eNJ1Qtw1bqIquXSYKeE12H45-BH3yeNRLJOGZyWXk44eaqrALxRQwTVAQTzU_bxTjMBHkdKCU1kwMG9XTejcKpF5_V_fn9MZr1o1xl1p71Nw6j0aeZMyeNz93b15O7XRn-n1P19DYkGl2doQsnxdRw5uVbBibSJEHJiXXkPk1W1LmKeBLT-XPJM8b7HD4iSBbDpV30BmWxFTcWBA-LpxtntQqMofZFX50tcKD8-vkqbOS0CWq_sRcqmwPofubaCjNoacDrvpLd6defwVmlPAnqTQ7aN9gmG8X6nG8h91_jXZuPQwfj6lhblQb3X78cGBOqzzKWIYy9KWOOJdxNJUqRs7nJdRLRKsm038LHAeKdJaxpk9d4G17UtFPvsCXHoScqm7KZpOpC6OPz3wkoccqKewd7fUuPtctpqBwxHBvom6Wu6w9tM9M-fSrl_TnYsLS0jDRYdjtmrbrCy5pvLFKYQSCZyQAm1d7BWg8wP29GLQyi581xsSv-s60Z1FRi56hnMzIHr0SfSCsFneml-63b0JbD4dswxM_GWmQzhf4CBlhazN-rnqkSxZZCP5mtzRTs12ppAccXs14S2f51UExclUADIWyr7EY4tz3vOXgpOuQbd26uIbuRZCj8GHsyXVy-J1ovKFjiGH9nKxfVQYJvQL7o1V0G9IA0czQ6R9QFHfxB1SI7U9Us3JdLdzrJ7DSJfOlVThfNoUyABdYdERhuhoC315l_D35yEcOpocX_4EvR_HD10LsoErVuWDa4M5XNiduEFlNtlUcev35zS2LEVSJpTnRvCD4tUZWKxNe_hHn5GzDaUYaMbB1bbItmPRmNiSspdDDKDf9ZJ8Nnntdw9cDtd6uagkg7ArSoSjHcjc6MDm7B-HWjMQQMpoHcGLcvUJwC78fvNpFosfSRJSHGVd3NdL1zQImo31FgKQDfXa4044BBm-hiT2_mNVQUr2bl6m9vqkEnbLgVzr22N9_ire4BKnVd9gs5DtguIL2aXXolPDmF14q4Yk4BhUxCVRiD4Tz29po4q3XWJHQ1AyJMmydkm6FSCsCn5ha6yqNrju71otjgYuU5Nt-Z8yYZa98FLhSokfiKNw2zHsnjm-qq1OWgKXUX_tDfbHQXWq-ymjUbT9Hi4sZWeO6l56VaB-iMiph5ScAINq6x0ucO5Rb087iRZZpooG1E0c5vxVtfv2HC2MyGWrWvDRiq2w15cXCpDFDgQC_t_4UhrJ0CRynLAXnX358tk364i07ANVGGlhiQKoEJxg4Q5Yv1dcOuyyTKcwsh-0MsF0qccKT9JrfejqZFu3CFKx2t23NJKdDxBmYG4_K2LVTUzsSGD8fEbaTS16azYkMq9KYquB3hZCXHjQuvu9MXIpCoZCitCS5LT-DwnFH5K1oxvYalQA6XPyfAFFS1zWvDVxOv0RvWxG7l0Y2-jeOCgdKnewYExFLfjC3wxv4hO4lk2L1H6EcuOo4AQYQwxJzHHkEw07r6hC60w2q9WwYL1cYaXcFLvvT5HamushF5wc4ogPzeo-vMB8XbdXYDnIBdevdY7WyArqjFwBPdHnxLXO9ASEXUaRIXDVy9v_fUcmcVDZIn6G3e1uTo_5ECu-xzzer5QFAp1mNASr4KlEnKwYIZWwPQJ6_wNNnuz9eg8j8HiQGdJO2gRZZvHMazyvxKgXTMcoQ9gtzShOEY_8TOJYVCF6Tg1o__gZihxM1hEBzbFoTy3i3G_QRkexeRayqegkPc8JZP7kbzYNQjN8W5ArOwws24fQZaOH54pqD-dkYc5GeFtzMm40v8hWSxOSpUXZnKhsvd3_euMTrrJhHckWUdj4hYOUyB1LD71Enfkov3OTkAYVvGZodBM9oO2t7vVtWeBL9HXx0cFQgVVzMzMPRmBx6Dz-Y9r9Gz505srqDpgF6z_ClJjfH_vIW51wDAQz9VgRfYKvmSWAuF94VxdI3s58M0eaM","protected":"eyJlcGsiOnsia3R5IjoiT0tQIiwiY3J2IjoiWDI1NTE5IiwieCI6IjVtYTJNcUFEM295cU11X3d2RHdJVFFJWm43UTRNQkhicXdaVE43OVhwRHMifSwiYXB2IjoicFpXOFdTWm9YNnNrXzNaRm1ramFtWXBpOGZGUVlwQ3h2RFBlaUJwT3BrMCIsImVuYyI6IlhDMjBQIiwiYWxnIjoiRUNESC1FUytBMjU2S1cifQ","recipients":[{"encrypted_key":"QifvalQ7BJpXotPnf7bj76IbWew3eNLtULmwDcJ_W13bHrP3JCpiQg","header":{"kid":"did:peer:2.Ez6LSpou63sBDB4FGpbVM23bECgZnkMHj6hGmA3PgQByR9fs4.Vz6MkhNpHBCUgBgkCbiM4zMjrbfgGowwEuEchmzf6J5W3av8E.SeyJ0IjoiZG0iLCJzIjoiaHR0cHM6Ly9sb2NhbGhvc3Q6ODA4MC8iLCJyIjpbXSwiYSI6WyJkaWRjb21tL3YyIl19#6LSpou63sBDB4FGpbVM23bECgZnkMHj6hGmA3PgQByR9fs4"}}],"tag":"1DK3GmQ41inpuZcpu_6TuQ","iv":"ZJDpJg-j7buDQP3TlemN1fKFHbRwaEgy"}' #Anon
object Agent1Mediators {
  // "did:peer:2.Ez6LSdeknAoZHfA2QWsvbRbh5UZ1NPHP38NjBDdHrrhACJUgh.Vz6MkeTY54nNujTFzUQH6DuERrqXjwKe1dbKDa7nzFS8GNPq1"
  // "did:peer:2.Ez6LSdeknAoZHfA2QWsvbRbh5UZ1NPHP38NjBDdHrrhACJUgh.Vz6MkeTY54nNujTFzUQH6DuERrqXjwKe1dbKDa7nzFS8GNPq1.SeyJ0IjoiZG0iLCJzIjoiZGlkOnBlZXI6Mi5FejZMU3BvdTYzc0JEQjRGR3BiVk0yM2JFQ2dabmtNSGo2aEdtQTNQZ1FCeVI5ZnM0LlZ6Nk1raE5wSEJDVWdCZ2tDYmlNNHpNanJiZmdHb3d3RXVFY2htemY2SjVXM2F2OEUuU2V5SjBJam9pWkcwaUxDSnpJam9pYUhSMGNITTZMeTlzYjJOaGJHaHZjM1E2T0RBNE1DOGlMQ0p5SWpwYlhTd2lZU0k2V3lKa2FXUmpiMjF0TDNZeUlsMTkiLCJyIjpbXSwiYSI6WyJkaWRjb21tL3YyIl19"
  val agent = DIDPeer2.makeAgent(
    Seq(keyAgreement, keyAuthentication),
    Seq(DIDPeerServiceEncoded(Agent0Mediators.agent.id))
  )

  def agentLayer: ULayer[Agent] = ZLayer.succeed(agent)
  def keyAgreement =
    OKPPrivateKey(
      kty = KTY.OKP,
      crv = Curve.X25519,
      d = "ZwV0ySU6tXxQlAzw8ji79H8bdeve6vGzruG_SYJen5Q",
      x = "HVo4aqtv6pL660P_4yBWQtVZLQrw0kqhzzKPyaI1-EI",
      kid = None
    )
  def keyAuthentication =
    OKPPrivateKey(
      kty = KTY.OKP,
      crv = Curve.Ed25519,
      d = "4OQpka1tpCMhz-oNpeobkmDg2NiD4JIisH5WBB1etMM",
      x = "ABIeuNr5_0rfwXGC2dgCd8Ab8AqfcB3DGrDwk70PJtA",
      kid = None
    )

}

object Agent2Mediators {
  // "did:peer:2.Ez6LSq12DePnP5rSzuuy2HDNyVshdraAbKzywSBq6KweFZ3WH.Vz6MksEtp5uusk11aUuwRHzdwfTxJBUaKaUVVXwFSVsmUkxKF"
  // "did:peer:2.Ez2nwUrQ6jGeHa8247VuGp4o4GDeoCEFjb8oLKbxFDTgGyxHbHRq.Vz6MksEtp5uusk11aUuwRHzdwfTxJBUaKaUVVXwFSVsmUkxKF.SeyJ0IjoiZG0iLCJzIjoiZGlkOnBlZXI6Mi5FejZMU2Rla25Bb1pIZkEyUVdzdmJSYmg1VVoxTlBIUDM4TmpCRGRIcnJoQUNKVWdoLlZ6Nk1rZVRZNTRuTnVqVEZ6VVFINkR1RVJycVhqd0tlMWRiS0RhN256RlM4R05QcTEuU2V5SjBJam9pWkcwaUxDSnpJam9pWkdsa09uQmxaWEk2TWk1RmVqWk1VM0J2ZFRZemMwSkVRalJHUjNCaVZrMHlNMkpGUTJkYWJtdE5TR28yYUVkdFFUTlFaMUZDZVZJNVpuTTBMbFo2TmsxcmFFNXdTRUpEVldkQ1oydERZbWxOTkhwTmFuSmlabWRIYjNkM1JYVkZZMmh0ZW1ZMlNqVlhNMkYyT0VVdVUyVjVTakJKYW05cFdrY3dhVXhEU25wSmFtOXBZVWhTTUdOSVRUWk1lVGx6WWpKT2FHSkhhSFpqTTFFMlQwUkJORTFET0dsTVEwcDVTV3B3WWxoVGQybFpVMGsyVjNsS2EyRlhVbXBpTWpGMFRETlplVWxzTVRraUxDSnlJanBiWFN3aVlTSTZXeUprYVdSamIyMXRMM1l5SWwxOSIsInIiOltdLCJhIjpbImRpZGNvbW0vdjIiXX0"
  val agent = DIDPeer2.makeAgent(
    Seq(keyAgreement, keyAuthentication),
    Seq(DIDPeerServiceEncoded(Agent1Mediators.agent.id))
  )
  def agentLayer: ULayer[Agent] = ZLayer.succeed(agent)
  def keyAgreement =
    OKPPrivateKey(
      kty = KTY.OKP,
      crv = Curve.X25519,
      d = "9yAs1ddRaUq4d7_HfLw2VSj1oW2kirb2wALmPXrRuZA",
      x = "xfvZlkAnuNpssHOR2As4kUJ8zEPbowOIU5VbhBsYoGo-EI",
      kid = None
    )
  def keyAuthentication =
    OKPPrivateKey(
      kty = KTY.OKP,
      crv = Curve.Ed25519,
      d = "-yjzvLY5dhFEuIsQcebEejbLbl3b8ICR7b2y2_HqFns",
      x = "vfzzx6IIWdBI7J4eEPHuxaXGErhH3QXnRSQd0d_yn0Y",
      kid = None
    )

}
