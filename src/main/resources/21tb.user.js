// ==UserScript==
// @name         21tb
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       You
// @match       http://sdnsyh.21tb.com/ems/html/examCenter/fullExamTemp.do?*
// @run-at            document-end
// @grant             unsafeWindow
// ==/UserScript==

(function() {
    'use strict';
    console.log('#');

    // Your code here...
            console.log('备注');
        

        $('div.question-stem').each(function (m, val) {
//          var question = $(this).text().replace("\r\n","").replace("（1 分）","").replace("（2 分）","").replace("（3 分）","").replace("（4 分）","").replace("（5 分）","").trim();
          var question = $(this).text();
          //console.log(question);
          var start=question.indexOf(".");
          var end=question.lastIndexOf("（");
         // console.log(question.substr(start+1,end).trim());
          var i = 0;
		  for (; i < qs.length; i++)
            {
                if (question.includes(qs[i]) ) {
                                   //console.log(i);
//   var answer = as[i].
                  //console.log(as[i]);
                    $(this).append(as[i]);

                    break;
                }
            }


        });

        console.log('hello world!');
})();
