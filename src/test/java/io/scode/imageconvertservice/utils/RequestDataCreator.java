package io.scode.imageconvertservice.utils;

import java.util.ArrayList;
import java.util.List;

public class RequestDataCreator {

    public List<String> getSafeRequestList(){
        List<String> result = new ArrayList<>();

        result.add(getBasicRequest());
        result.add(getBgImageRequest());
        result.add(getFontSource());

        return result;
    }

    public String getBasicRequest() {
        return DataCodec.encodeBase64("""
                <style>
                    *,
                    *::before,
                    *::after {
                      box-sizing: border-box;
                    }
                                
                    img {
                      display: block;
                    }
                                
                    .gallery {
                      position: relative;
                      z-index: 2;
                      padding: 10px;
                      display: flex;
                      flex-flow: row wrap;
                      justify-content: space-between;
                      transition: all .5s ease-in-out;
                      transform: translateZ(0);
                                
                      &.pop {
                        filter: blur(10px);
                      }
                                
                      figure {
                        flex-basis: 33.333%;
                        padding: 10px;
                        overflow: hidden;
                        cursor: pointer;
                                
                        img {
                          width: 100%;
                          transition: all .3s ease-in-out;
                        }
                       \s
                        figcaption {
                          display: none;
                        }
                       \s
                      }
                    }
                                
                    .popup {
                      position: fixed;
                      z-index: 2;
                      top: 0;
                      left: 0;
                      width: 100%;
                      height: 100vh;
                      background: #fff;
                      opacity: 0;
                      transition: opacity .5s ease-in-out .2s;
                                
                                
                      &.pop {
                        opacity: 1;
                        transition: opacity .2s ease-in-out 0s;
                                
                        figure {
                          margin-top: 0;
                          opacity: 1;
                        }
                      }
                                
                      figure {
                        position: absolute;
                        top: 50%;
                        left: 50%;
                        transform: translate(-50%, -50%);
                        transform-origin: 0 0;
                        margin-top: 30px;
                        opacity: 0;
                        animation: poppy 500ms linear both;
                                
                        img {
                          position: relative;
                          z-index: 2;
                          //box-shadow: 0 1px 5px rgba(0, 0, 0, .2), 0 6px 30px rgba(0, 0, 0, .4);
                        }
                       \s
                        figcaption {
                          position: absolute;
                          bottom: 50px;
                          background: linear-gradient(180deg, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.78));
                          z-index: 2;
                          width: 100%;
                          padding: 100px 20px 20px 20px;
                          color: #fff;
                          font-family: 'Open Sans', sans-serif;
                          font-size: 32px;
                         \s
                          small {
                            font-size: 11px;
                            display: block;
                            text-transform: uppercase;
                            margin-top: 12px;
                            text-indent: 3px;
                            opacity: .7;
                            letter-spacing: 1px;
                          }
                        }
                                
                        .shadow {
                          position: relative;
                          z-index: 1;
                          top: -56px;
                          margin: 0 auto;
                          background-position: center bottom;
                          background-repeat: no-repeat;
                          width: 98%;
                          height: 50px;
                          opacity: .9;
                          filter: blur(16px) contrast(1.5);
                          transform: scale(0.95, -0.7);
                          transform-origin: center bottom;
                        }
                      }
                                
                      .close {
                        position: absolute;
                        z-index: 3;
                        top: 10px;
                        right: 10px;
                        width: 25px;
                        height: 25px;
                        cursor: pointer;
                        background: url(#close);
                        border-radius: 25px;
                        background: rgba(0, 0, 0, .1);
                        box-shadow: 0 0 3px rgba(0, 0, 0, .2);
                                
                        svg {
                          width: 100%;
                          height: 100%;
                        }
                      }
                    }
                </style>
                <div class="gallery">
                  <figure>
                    <img src="https://images.unsplash.com/photo-1448814100339-234df1d4005d?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=600" alt="" />
                    <figcaption>Daytona Beach <small>United States</small></figcaption>
                  </figure>
                  <figure>
                    <img src="https://images.unsplash.com/photo-1443890923422-7819ed4101c0?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=600" alt="" />
                    <figcaption>Териберка, gorod Severomorsk <small>Russia</small></figcaption>
                  </figure>
                  <figure>
                    <img src="https://images.unsplash.com/photo-1445964047600-cdbdb873673d?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=600" alt="" />
                    <figcaption>
                      Bad Pyrmont <small>Deutschland</small>
                    </figcaption>
                  </figure>
                  <figure>
                    <img src="https://images.unsplash.com/photo-1439798060585-62ab242d7724?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=600" alt="" />
                    <figcaption>Yellowstone National Park <small>United States</small></figcaption>
                  </figure>
                  <figure>
                    <img src="https://images.unsplash.com/photo-1440339738560-7ea831bf5244?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=600" alt="" />
                    <figcaption>Quiraing, Portree <small>United Kingdom</small></figcaption>
                  </figure>
                  <figure>
                    <img src="https://images.unsplash.com/photo-1441906363162-903afd0d3d52?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=600" alt="" />
                    <figcaption>Highlands <small>United States</small></figcaption>
                  </figure>
                  <figure>
                    <img src="https://images.unsplash.com/photo-1448814100339-234df1d4005d?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=600" alt="" />
                    <figcaption>Daytona Beach <small>United States</small></figcaption>
                  </figure>
                  <figure>
                    <img src="https://images.unsplash.com/photo-1443890923422-7819ed4101c0?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=600" alt="" />
                    <figcaption>Териберка, gorod Severomorsk <small>Russia</small></figcaption>
                  </figure>
                  <figure>
                    <img src="https://images.unsplash.com/photo-1445964047600-cdbdb873673d?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=600" alt="" />
                    <figcaption>
                      Bad Pyrmont <small>Deutschland</small>
                    </figcaption>
                  </figure>
                  <figure>
                    <img src="https://images.unsplash.com/photo-1439798060585-62ab242d7724?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=600" alt="" />
                    <figcaption>Yellowstone National Park <small>United States</small></figcaption>
                  </figure>
                  <figure>
                    <img src="https://images.unsplash.com/photo-1440339738560-7ea831bf5244?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=600" alt="" />
                    <figcaption>Quiraing, Portree <small>United Kingdom</small></figcaption>
                  </figure>
                  <figure>
                    <img src="https://images.unsplash.com/photo-1441906363162-903afd0d3d52?crop=entropy&fit=crop&fm=jpg&h=400&ixjsv=2.1.0&ixlib=rb-0.3.5&q=80&w=600" alt="" />
                    <figcaption>Highlands <small>United States</small></figcaption>
                  </figure>
                </div>
                    """);
    }


//    public String getBasicRequest() {
//        return DataCodec.encodeBase64("""
//                <style>
//                .col-centered {
//                    float: none;
//                    margin: 0 auto;
//                }
//
//                .carousel-control {
//                    width: 8%;
//                    width: 0px;
//                }
//                .carousel-control.left,
//                .carousel-control.right {
//                    margin-right: 40px;
//                    margin-left: 32px;
//                    background-image: none;
//                    opacity: 1;
//                }
//                .carousel-control > a > span {
//                    color: white;
//                	  font-size: 29px !important;
//                }
//
//                .carousel-col {
//                    position: relative;
//                    min-height: 1px;
//                    padding: 5px;
//                    float: left;
//                 }
//
//                .block {
//                	width: 306px;
//                	height: 230px;
//                }
//
//                .red {background: red;}
//
//                .blue {background: blue;}
//
//                .green {background: green;}
//
//                .yellow {background: yellow;}
//                </style>
//                <div class="container">
//                	<div class="row">
//                		<div class="col-xs-11 col-md-10 col-centered">
//
//                			<div id="carousel" class="carousel slide" data-ride="carousel" data-type="multi" data-interval="2500">
//                				<div class="carousel-inner">
//                					<div class="item active">
//                						<div class="carousel-col">
//                							<div class="block red img-responsive"></div>
//                						</div>
//                					</div>
//                					<div class="item">
//                						<div class="carousel-col">
//                							<div class="block green img-responsive"></div>
//                						</div>
//                					</div>
//                					<div class="item">
//                						<div class="carousel-col">
//                							<div class="block blue img-responsive"></div>
//                						</div>
//                					</div>
//                					<div class="item">
//                						<div class="carousel-col">
//                							<div class="block yellow img-responsive"></div>
//                						</div>
//                					</div>
//                				</div>
//
//                				<!-- Controls -->
//                				<div class="left carousel-control">
//                					<a href="#carousel" role="button" data-slide="prev">
//                						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
//                						<span class="sr-only">Previous</span>
//                					</a>
//                				</div>
//                				<div class="right carousel-control">
//                					<a href="#carousel" role="button" data-slide="next">
//                						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
//                						<span class="sr-only">Next</span>
//                					</a>
//                				</div>
//                			</div>
//
//                		</div>
//                	</div>
//                </div>
//                """);
//    }

//    public String getBasicRequest() {
//        return DataCodec.encodeBase64("""
//                <style>
//                div {
//                  background-color: green;
//                  color: white;
//                  width: 100px;
//                  height: 100px;
//                }
//                </style>
//                <div>
//                  에스코드 폰트 적용
//                </div>""");
//    }

    public String getBgImageRequest() {
        return DataCodec.encodeBase64("""
                <style>
                div {
                  background-image: url('https://picsum.photos/200/300');
                  width: 100px;
                  height: 100px;
                }
                </style>
                <div>
                  에스코드
                </div>
                """);
    }

    public String getFontSource() {
        return DataCodec.encodeBase64("""
                <style>
                div {
                  width: 100px;
                  height: 100px;
                  background-color: black;
                  color: white;
                }
                </style>
                <div>
                  에스코드 폰트 적용
                </div>
                """);
    }


}
