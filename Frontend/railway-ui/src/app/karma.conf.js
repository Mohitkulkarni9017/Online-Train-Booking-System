module.exports = function(config) {
    config.set({
      basePath: '',
      frameworks: ['jasmine', '@angular-devkit/build-angular'],
      plugins: [
        require('karma-jasmine'),
        require('karma-chrome-launcher'),
        require('@angular-devkit/build-angular/plugins/karma')
      ],
      browsers: ['Chrome'],
      singleRun: true,
      files: [
        // Include your component's TypeScript files and test files
      ],
      proxies: {
        '/assets/': '/base/src/assets/'
      },
      port: 9876,
      colors: true,
      logLevel: config.LOG_INFO,
      autoWatch: true,
      browsers: ['Chrome'],
      customLaunchers: {
        ChromeHeadless: {
          base: 'Chrome',
          flags: [
            '--no-sandbox',
            '--headless',
            '--disable-gpu',
            '--disable-translate',
            '--disable-extensions'
          ]
        }
      },
      concurrency: Infinity,
      captureTimeout: 60000,
      browserDisconnectTimeout: 10000,
      browserDisconnectTolerance: 3,
      browserNoActivityTimeout: 60000
    });
  };
  