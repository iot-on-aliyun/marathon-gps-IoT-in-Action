// pages/map/map.js
Page({
  data: {
    markers: [{
      iconPath: '/images/running.png',
      id: 0,
      latitude: 30.208587,
      longitude: 120.196856,
      width: 36,
      height: 36,
    }],

    polyline: [],
    polygons: [],
    controls: [{
      id: 1,
      iconPath: '/resources/location.png',
      position: {
        left: 0,
        top: 300 - 50,
        width: 50,
        height: 50
      },
      clickable: true
    }]
  },


  regionchange(e) {
    console.log(e.type)
  },
  markertap(e) {
    console.log(e.markerId)
  },
  controltap(e) {
    console.log(e.controlId)
  },
  onShow: function () {
    /*
    真实场景这里调用你API获取数据
    wx.request({
      url: '',
    })
    */
    //路线模拟数据
    this.makePolyline()
    //地理围栏模拟数据
    this.makePolygons()
  },
  makePolyline: function ()  {
    var polyline = {
      points: [{
          longitude: 120.136539,
          latitude: 30.268076
        },
        {
          longitude: 120.137182,
          latitude: 30.264406
        },
        {
          longitude: 120.147396,
          latitude: 30.267316
        },
        {
          longitude: 120.148126,
          latitude: 30.26563
        },
        {
          longitude: 120.150272,
          latitude: 30.26411
        },
        {
          longitude: 120.15437,
          latitude: 30.260107
        },
        {
          longitude: 120.15791,
          latitude: 30.261107
        },
        {
          longitude: 120.15864,
          latitude: 30.258179
        },
        {
          longitude: 120.160464,
          latitude: 30.25551
        },
        {
          longitude: 120.162009,
          latitude: 30.253045
        },
        {
          longitude: 120.162996,
          latitude: 30.251506
        },
        {
          longitude: 120.163189,
          latitude: 30.250468
        },
        {
          longitude: 120.163189,
          latitude: 30.250468
        },
        {
          longitude: 120.177437,
          latitude: 30.25045
        },
        {
          longitude: 120.186106,
          latitude: 30.249097
        },
        {
          longitude: 120.189925,
          latitude: 30.248077
        },
        {
          longitude: 120.199989,
          latitude: 30.247484
        },
        {
          longitude: 120.20634,
          latitude: 30.245723
        },
        {
          longitude: 120.20428,
          latitude: 30.242665
        },
        {
          longitude: 120.201126,
          latitude: 30.239291
        },
        {
          longitude: 120.194753,
          latitude: 30.233692
        },
        {
          longitude: 120.190869,
          latitude: 30.229447
        },
        {
          longitude: 120.184926,
          latitude: 30.223365
        },
        {
          longitude: 120.182029,
          latitude: 30.220492
        },
        {
          longitude: 120.177265,
          latitude: 30.217358
        },
        {
          longitude: 120.174326,
          latitude: 30.215652
        },
        {
          longitude: 120.176643,
          latitude: 30.211962
        },
        {
          longitude: 120.17954,
          latitude: 30.207419
        },
        {
          longitude: 120.182522,
          latitude: 30.202746
        },
        {
          longitude: 120.185569,
          latitude: 30.197905
        },
        {
          longitude: 120.183467,
          latitude: 30.202282
        },
        {
          longitude: 120.196856,
          latitude: 30.208587
        },
      ],
      color: '#0000FFff',
      width: 4,
      arrowLine: true,
      dottedLine: false
    }
    this.setData({
      polyline: [polyline]
    })
  },
  makePolygons: function() {
    var polygon = {
      points: [{
          longitude: 120.127784,
          latitude: 30.265277
        }, {
          longitude: 120.133921,
          latitude: 30.263276
        },
        {
          longitude: 120.137097,
          latitude: 30.264425
        },
        {
          longitude: 120.136582,
          latitude: 30.271097
        },
        {
          longitude: 120.130445,
          latitude: 30.271356
        }
      ],
      fillColor: "#FF0000AA",
      strokeColor: "#000000DD",
      strokeWidth: 3
    }
    this.setData({
      polygons: [polygon]
    })
  }


})